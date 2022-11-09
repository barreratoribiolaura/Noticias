package com.example.practica3a.DataBase
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.practica3a.Clases.NoticiaEntity
import com.example.practica3a.Clases.UsuarioEntity
import com.example.practica3a.Interfaces.NoticiaDao
import com.example.practica3a.Interfaces.UsuarioDao

@Database(entities = arrayOf( NoticiaEntity::class,UsuarioEntity::class), version = 2)
abstract class BaseDeDatos2 : RoomDatabase(){
        abstract fun noticiaDao(): NoticiaDao
        abstract fun usuarioDao(): UsuarioDao

}