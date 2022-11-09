package com.example.practica3a.Interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practica3a.Clases.NoticiaEntity
import com.example.practica3a.Clases.UsuarioEntity

@Dao
interface UsuarioDao {

    @Query("SELECT * FROM UsuarioEntity where nombre = :nombre and contrasenya = :contrasenya")
    fun getUsuario(nombre:String,contrasenya:String): UsuarioEntity

    @Insert
    fun addUsuario(usuarioEntity: UsuarioEntity)

    @Update
    fun updateUsuario(usuarioEntity: UsuarioEntity)

    @Delete
    fun deleteUsuario(usuarioEntity: UsuarioEntity)


}