package com.example.practica3a.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica3a.Activities.NoticiasActivity
import com.example.practica3a.Clases.NoticiaEntity
import com.example.practica3a.R
import com.example.practica3a.databinding.ItemNoticiaBinding


class NoticiaAdapter(private var noticias:MutableList<NoticiaEntity>, private val
listener: NoticiasActivity
):RecyclerView.Adapter<NoticiaAdapter.ViewHolder>(){

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
            binding.nombre.text = noticia.titulo
            binding.resumen.text = noticia.resumen
            binding.fecha.text= noticia.fecha
            Glide.with(context)
                .load(noticia.imagenurl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .into(binding.imgNoticia)
        }

    }

    fun setNoticias(noticias: MutableList<NoticiaEntity>) {
        this.noticias = noticias
        notifyDataSetChanged()
    }

    fun deleteNoticia(noticiaEntity: NoticiaEntity){
        val index = noticias.indexOf(noticiaEntity)
        if (index != -1){
            noticias.removeAt(index)
            notifyItemRemoved(index)
        }

    }

    override fun getItemCount(): Int = noticias.size

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val binding = ItemNoticiaBinding.bind(view)

        fun setListener(noticia: NoticiaEntity) {
            binding.root.setOnClickListener { listener.onClick(noticia) }
            binding.root.setOnLongClickListener{
                listener.onDeleteNoticia(noticia)
                true
            }


        }


    }




}