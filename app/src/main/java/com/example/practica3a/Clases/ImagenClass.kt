package com.example.practica3a.Clases

import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class ImagenClass (){

    fun loadImage(url: String,iv: ImageView,act:AppCompatActivity) {
        Glide.with(act)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(iv)
    }
}