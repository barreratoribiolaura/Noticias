package com.example.practica3a.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.practica3a.Clases.ImagenClass
import com.example.practica3a.Clases.NoticiaEntity
import com.example.practica3a.Clases.UsuarioEntity
import com.example.practica3a.DataBase.BaseDeDatos2
import com.example.practica3a.DataBase.NoticiaApplication
import com.example.practica3a.R
import com.example.practica3a.databinding.ActivityRegistroBinding
import com.google.android.material.textfield.TextInputLayout


class RegistroActivity : AppCompatActivity(){

    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        añadirFondo()

        binding.btnRegistrarse.setOnClickListener(){
            deleteDatabase("NoticiaDataBase");
            registrarUsuario()
        }
    }

    private fun añadirFondo(){
        val i = ImagenClass()
        i.loadImage("https://images.unsplash.com/photo-1629196914168-3a2652305f9f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",binding.imgLogin,this)

    }

    private fun registrarUsuario(){

        val nombretil = binding.tilNombre
        val contrasenyatil = binding.tilContraseA

        if (comprobarTextInputs(nombretil,contrasenyatil)){
                val usuario = UsuarioEntity(
                    nombre = nombretil.editText?.text.toString(),
                    contrasenya = contrasenyatil.editText?.text.toString()
                )

                Thread {
                    NoticiaApplication.database.usuarioDao().addUsuario(usuario)
                }.start()

            entrar()
        }

    }

    private fun comprobarTextInputs(nombretxt: TextInputLayout, contrasenyatxt: TextInputLayout) : Boolean{

        var valido = true

        val lista:List<TextInputLayout> = listOf(nombretxt,contrasenyatxt)
        for (item in lista){
            if (item.editText?.text!!.isEmpty()){
                item.helperText= "REQUERIDO"
                item.isHelperTextEnabled=true
                valido = false
            }else{
                item.isHelperTextEnabled=false
            }
        }
        return valido
    }

    private fun entrar(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }


}
