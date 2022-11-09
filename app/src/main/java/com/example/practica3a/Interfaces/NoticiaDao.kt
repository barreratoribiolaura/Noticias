package com.example.practica3a.Interfaces

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.practica3a.Clases.NoticiaEntity

@Dao
interface NoticiaDao {

    @Query("SELECT * FROM NoticiaEntity")
    fun getAllNoticias():MutableList<NoticiaEntity>

    @Insert
    fun addNoticia(noticiaEntity: NoticiaEntity)

    @Update
    fun updateNoticia(noticiaEntity: NoticiaEntity)

    @Delete
    fun deleteNoticia(noticiaEntity: NoticiaEntity)


}