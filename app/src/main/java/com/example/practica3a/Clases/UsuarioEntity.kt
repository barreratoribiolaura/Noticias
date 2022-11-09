package com.example.practica3a.Clases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UsuarioEntity")
data class UsuarioEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var nombre: String,
    var contrasenya: String,
)
