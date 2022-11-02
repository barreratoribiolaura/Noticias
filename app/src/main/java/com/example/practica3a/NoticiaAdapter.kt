package com.example.practica3a

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica3a.databinding.ItemNoticiaBinding


class NoticiaAdapter(private val noticias:List<Noticia>, private val
listener: OnClickListener):RecyclerView.Adapter<NoticiaAdapter.ViewHolder>(){

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_noticia,parent,false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noticia = noticias.get(position)
        with(holder){
            setListener(noticia)
            binding.nombre.text = noticia.name
            binding.resumen.text = noticia.resumen
            binding.fecha.text= noticia.fecha
            Glide.with(context)
                .load(noticia.imagen)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgNoticia)
        }

    }

    override fun getItemCount(): Int = noticias.size

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ItemNoticiaBinding.bind(view)

        fun setListener(noticia:Noticia) {
            binding.root.setOnClickListener { listener.onClick(noticia) }
        }
    }




}