package com.example.practica3a.DataBase

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

class NoticiaApplication: Application() {

    companion object {
        lateinit var database: BaseDeDatos2
    }

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            BaseDeDatos2::class.java,
            "BaseDeDatos2")
            .build()
    }
}