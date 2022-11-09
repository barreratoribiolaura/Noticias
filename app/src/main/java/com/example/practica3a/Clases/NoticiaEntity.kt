package com.example.practica3a.Clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NoticiaEntity")
data class NoticiaEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var titulo: String,
    var resumen: String = "",
    var fecha: String  = "",
    var imagenurl: String  = "",
    var enlace: String = "")

