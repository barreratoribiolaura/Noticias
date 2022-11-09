package com.example.practica3a.Activities

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.practica3a.Clases.ImagenClass
import com.example.practica3a.DataBase.NoticiaApplication
import com.example.practica3a.databinding.ActivityMainBinding
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.awaitAll
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        añadirFondo()

        val userDetails = getSharedPreferences("userdetails", MODE_PRIVATE)

        comprobarCheck(userDetails)

        binding.btnLogin.setOnClickListener(){
            login(userDetails)
        }
        binding.btnRegister.setOnClickListener(){
            val intent = Intent(this, RegistroActivity::class.java)
            startActivity(intent)
        }
    }


    private fun añadirFondo(){
        val i = ImagenClass()
        i.loadImage("https://images.unsplash.com/photo-1629196914168-3a2652305f9f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",binding.imgLogin,this)

    }

    private fun comprobarCheck(userDetails:SharedPreferences){
        val recordar = userDetails.getString("recordar","")
        if(recordar=="true"){
            entrar()
        }
    }

    private fun login(userDetails:SharedPreferences){

        val nombretil = binding.tilNombre
        val contrasenyatil = binding.tilContraseA

        if (comprobarTextInputs(nombretil,contrasenyatil)){
            if(comprobarUsuarioBD()){
                if(binding.checkbox.isChecked){
                    recordarUsuario(nombretil.editText?.text.toString(),contrasenyatil.editText?.text.toString(),userDetails)
                }else{
                    olvidarUsuario(userDetails)
                }
                Toast.makeText(this,"Bienvenido/a", Toast.LENGTH_SHORT).show()
                entrar()
            }else{
                Toast.makeText(this,"El usuario no existe", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun comprobarTextInputs(nombretxt: TextInputLayout,contrasenyatxt: TextInputLayout) : Boolean{

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

    private fun comprobarUsuarioBD() : Boolean{

        var existe = false

        val nombre = binding.inputNombre.text.toString()
        val contrasenya = binding.inputContrasenya.text.toString()

        doAsync {
            val u = NoticiaApplication.database.usuarioDao().getUsuario(nombre, contrasenya)
            if(u.nombre.isNotEmpty()){
                existe = true
            }
        }

        Thread.sleep(70)

        return existe
    }

    private fun recordarUsuario(nombre:String,contrasenya:String,userDetails:SharedPreferences){

            val edit: SharedPreferences.Editor = userDetails.edit()
            edit.putString("usuario", nombre.trim())
            edit.putString("contrasenya", contrasenya.trim())
            edit.putString("recordar","true")
            edit.apply()
    }

    private fun olvidarUsuario(userDetails:SharedPreferences){
        val edit: SharedPreferences.Editor = userDetails.edit()
        edit.clear()
        edit.apply()

    }
    private fun entrar(){
        val intent = Intent(this, NoticiasActivity::class.java)
        startActivity(intent)
    }
}




