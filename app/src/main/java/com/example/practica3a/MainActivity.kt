package com.example.practica3a

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica3a.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userDetails = getSharedPreferences("userdetails", MODE_PRIVATE)

        recordarUsuario(userDetails)

        loadImage("https://images.unsplash.com/photo-1629196914168-3a2652305f9f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",binding.imgLogin)

        binding.btnLogin.setOnClickListener(){
            guardarUsuario(userDetails)
            comprobarVacio()
        }
    }

    //Glide
    private fun loadImage(
        url: String,
        iv: ImageView
    ) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(iv)
    }

    private fun recordarUsuario(userDetails: SharedPreferences){

        var spuserName: String? = userDetails.getString("usuario","")
        var sppassword: String? = userDetails.getString("contrasenya", "")

        binding.tilNombre.editText!!.setText(spuserName)
        binding.tilContraseA.editText!!.setText(sppassword)

    }

    private fun comprobarVacio(){

        if (binding.inputNombre.text!!.isNotEmpty() && binding.inputContrasenya.text!!.isNotEmpty()){
            val intent = Intent(this, NoticiasActivity::class.java)
            startActivity(intent)
        }else{
            Toast.makeText(this,"No has rellenado los campos", Toast.LENGTH_SHORT).show()
        }

    }

    private fun guardarUsuario(userDetails: SharedPreferences){

        val nombre = binding.inputNombre.text.toString()
        val contrasenya = binding.inputContrasenya.text.toString()


        if (nombre.isNotEmpty() && contrasenya.isNotEmpty()){
            val edit: SharedPreferences.Editor = userDetails.edit()
            edit.putString("usuario", nombre.trim())
            edit.putString("contrasenya", contrasenya.trim())
            edit.apply()
        }else{
            Toast.makeText(this,"No has rellenado los campos", Toast.LENGTH_SHORT).show()
        }

    }


}