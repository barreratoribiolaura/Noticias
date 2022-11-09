package com.example.practica3a.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.practica3a.Clases.ImagenClass
import com.example.practica3a.Adapters.NoticiaAdapter
import com.example.practica3a.Clases.NoticiaEntity
import com.example.practica3a.DataBase.NoticiaApplication

import com.example.practica3a.Interfaces.OnClickListener
import com.example.practica3a.databinding.ActivityNoticiaBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class NoticiasActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityNoticiaBinding
    private lateinit var noticiaAdapter: NoticiaAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    val openURL = Intent(Intent.ACTION_VIEW)
    private lateinit var mlayout: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        añadirFondo()


        setupRecyclerView()


        binding.btnadd.setOnClickListener() {
            formulario()
        }
    }

    private fun añadirFondo() {
        val i = ImagenClass()
        i.loadImage(
            "https://images.unsplash.com/photo-1629196914168-3a2652305f9f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",
            binding.imgLogin,
            this
        )

    }

    private fun formulario() {
        val intent = Intent(this, FormularioActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getNoticias(){

        doAsync {
            val noticias = NoticiaApplication.database.noticiaDao().getAllNoticias()
            uiThread {
                noticiaAdapter.setNoticias(noticias)
            }
        }


    }

    private fun setupRecyclerView() {

        noticiaAdapter = NoticiaAdapter(mutableListOf(), this)
        mlayout = LinearLayoutManager(this)
        getNoticias()
        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mlayout
            adapter = noticiaAdapter
        }
    }

    override
    fun onClick(noticia: NoticiaEntity){

        if(noticia.enlace.isNullOrBlank()){
            Toast.makeText(this,"Enlace externo", Toast.LENGTH_SHORT).show()
        }else{
            openURL.data= Uri.parse(noticia.enlace)
            startActivity(openURL)
        }

    }

    fun onDeleteNoticia(noticia: NoticiaEntity) {
        doAsync{
            NoticiaApplication.database.noticiaDao().deleteNoticia(noticia)
            uiThread {
                noticiaAdapter.deleteNoticia(noticia)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        
        val userDetails = getSharedPreferences("userdetails", MODE_PRIVATE)
        val edit: SharedPreferences.Editor = userDetails.edit()
        edit.clear()
        edit.apply()

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()


    }
}


