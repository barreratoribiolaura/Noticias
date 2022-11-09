package com.example.practica3a.Activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.practica3a.Adapters.NoticiaAdapter
import com.example.practica3a.Clases.ImagenClass
import com.example.practica3a.Clases.NoticiaEntity
import com.example.practica3a.DataBase.NoticiaApplication

import com.example.practica3a.R
import com.example.practica3a.databinding.ActivityFormularioBinding
import com.google.android.material.textfield.TextInputLayout


class FormularioActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormularioBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        binding = ActivityFormularioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnguardar.setOnClickListener(){
            comprobarFormulario()
        }

    }

    private fun comprobarFormulario(){
        var valido = true

        val titulotxt = binding.tilTitulo
        val resumentxt = binding.tilDescripcion
        val fechatxt = binding.tilFecha
        val imagenurltxt = binding.tilImagen
        val enlacetxt = binding.tilUrl

        val lista:List<TextInputLayout> = listOf(titulotxt,resumentxt,fechatxt)
        for (item in lista){
            if (item.editText?.text!!.isEmpty()){
                item.helperText= "REQUERIDO"
                item.isHelperTextEnabled=true

                valido = false
            }else{
                item.isHelperTextEnabled=false
            }
        }
        if(valido){
            val noticia = NoticiaEntity(
                titulo = titulotxt.editText?.text.toString(),
                resumen = resumentxt.editText?.text.toString(),
                fecha= fechatxt.editText?.text.toString(),
                imagenurl = imagenurltxt.editText?.text.toString(),
                enlace= enlacetxt.editText?.text.toString()
            )

            //Guardar en bd
            Thread{
                NoticiaApplication.database.noticiaDao().addNoticia(noticia)

            }.start()

            Thread.sleep(60)

            entrar()
        }



    }

    private fun entrar(){
        val intent = Intent(this, NoticiasActivity::class.java)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()

        val intent = Intent(this, NoticiasActivity::class.java)
        startActivity(intent)
        finish()

    }



}
