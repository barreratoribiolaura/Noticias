package com.example.practica3a

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.practica3a.databinding.ActivityMainBinding
import com.example.practica3a.databinding.ActivityNoticiaBinding
import com.example.practica3a.databinding.ItemNoticiaBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class NoticiasActivity : AppCompatActivity(), OnClickListener {

    lateinit var userDetails : SharedPreferences
    private lateinit var binding : ActivityNoticiaBinding
    private lateinit var noticiaAdapter: NoticiaAdapter
    private lateinit var linearLayoutManager: RecyclerView.LayoutManager
    val openURL = Intent(Intent.ACTION_VIEW)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userDetails = getSharedPreferences("userdetails", MODE_PRIVATE)

        comprobarUltimaNoticia(userDetails)

        noticiaAdapter = NoticiaAdapter(getNoticias(),this)
        linearLayoutManager = LinearLayoutManager(this)


        binding.recyclerView.apply {

            layoutManager = linearLayoutManager
            adapter = noticiaAdapter
        }

        loadImage("https://images.unsplash.com/photo-1629196914168-3a2652305f9f?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxleHBsb3JlLWZlZWR8Mnx8fGVufDB8fHx8&w=1000&q=80",binding.imgLogin)

    }

    //Glide
    private fun loadImage(
        url: String,
        iv: ImageView
    ) {
        Glide.with(this)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .centerCrop()
            .into(iv)
    }

    fun comprobarUltimaNoticia(userDetails : SharedPreferences){
        var ultima=userDetails.getString("ultimanoticia","")
        if(ultima!!.isNotEmpty()){
            //Toast.makeText(this,ultima, Toast.LENGTH_SHORT).show()

            Snackbar.make(
                binding.activitynoticia,
                "Reciente: " +ultima,
                BaseTransientBottomBar.LENGTH_SHORT
            ).show()

        }

    }

    override fun onClick(noticia: Noticia) {

        val edit: SharedPreferences.Editor = userDetails.edit()
        edit.putString("ultimanoticia", noticia.name)
        edit.apply()

        openURL.data= Uri.parse(noticia.enlace)
        startActivity(openURL)
    }

    private fun getNoticias() : MutableList<Noticia>{

        val noticias = mutableListOf<Noticia>()
        val n1 = Noticia(1,
            "Sonic Frontiers muestra nuevos gameplays que demuestran que la variedad " +
                    "de biomas puede ser una de sus fortalezas",
            "El título de SEGA ya ha finalizado su desarrollo, " +
                    "pero lo visto hasta ahora no es que haya convencido a muchos " +
                    "mientras que otros apostarán por él.",
            "25/10/2022",
            "https://i.blogs.es/2d8d8e/1366_910-8-/1024_682.jpeg",
            "https://www.3djuegos.com/juegos/sonic-frontiers/noticias/" +
                    "sonic-frontiers-muestra-nuevos-gameplays-que-demuestran-" +
                    "que-variedad-biomas-puede-ser-sus-fortalezas")

        val n2 = Noticia(2,"Genshin Impact nos prepara para la versión 3.2 " +
                "con nuevo personajes, un jefe y más.",
            "Genshin Impact sigue estableciéndose como uno de los juegos más " +
                    "populares del momento, algo que consigue gracias a una estrategia que combina " +
                    "su naturaleza de RPG gratuito con el lanzamiento frecuente de actualizaciones.",
            "hace 3 horas",
            "https://i.blogs.es/3ca363/genshin-impact/1366_910.jpeg",
            "https://www.3djuegos.com/juegos/genshin-impact/noticias/" +
                    "genshin-impact-nos-prepara-para-version-3-2-nuevo-" +
                    "personajes-jefe-este-su-trailer"
        )
        val n6 = Noticia(6,"Overwatch 2: Blizzard no elimina por completo " +
                "un modo personalizado que simula una violación",
            "Blizzard asegura haber borrado un modo personalizado de Overwatch 2 llamado " +
                    "'Sexual Harassment Simulator', pero todavía es accesible en el juego gratuito " +
                    "calificado como apto para mayores de 12 años.",
            "25/10/2022",
            "https://media.vandal.net/i/1280x720/10-2022/2022102518405329_2.jpg.webp",
            "https://vandal.elespanol.com/noticia/1350757537/overwatch-2-blizzard-no" +
                    "-elimina-por-completo-un-modo-personalizado-que-simula-una-violacion/")
        val n4 = Noticia(4,"Pokédex de Pokémon Escarlata y Púrpura:" +
                " Todos los Pokémon confirmados",
            "Estos son todos los Pokémon confirmados para la Pokédex de " +
                    "Pokémon Escarlata y Púrpura, una novena generación que se estrenará " +
                    "en Nintendo Switch el 18 de noviembre.",
            "hace 8 horas",
            "https://media.vandal.net/i/1280x720/8-2022/2022841729535_1.jpg.webp",
            "https://vandal.elespanol.com/noticia/1350755601/todos-los-pokemon-de-" +
                    "escarlata-y-purpura-iniciales-legendarios-formas-de-paldea/")
        val n5 = Noticia(5,"Battlefield 2042 introducirá los servidores permanentes.",
            "Desde EA son conscientes de que Battlefield 2042 ha tenido un lanzamiento " +
                    "accidentado, pues no son pocos los jugadores que criticaron la experiencia " +
                    "de juego del shooter.",
            "hace 1 hora",
            "https://i.blogs.es/722414/battlefield-2042/1366_910.jpeg",
            "https://www.3djuegos.com/juegos/battlefield-2042/noticias/battlefield" +
                    "-2042-introducira-servidores-permanentes-su-creacion-no-esta-abierta-" +
                    "a-todos-jugadores")
        val n3 = Noticia(3,"Doctor Who estrena nuevo logo y llegará en 2023 a Disney+",
            "Disney ha llegado a un acuerdo con BBC para que la nueva temporada de Doctor " +
                    "Who se estrene en su plataforma el próximo noviembre de 2023.",
            "13/10/2022",
            "https://image.api.playstation.com/vulcan/img/rnd/202011/1610/HWy4UZSiok6N" +
                    "Drkq4QkYJcAB.png",
            "https://vandal.elespanol.com/noticia/r18048/doctor-who-estrena-nuevo" +
                    "-logo-y-llegara-en-2023-a-disney")
        val n7 = Noticia(7,"Una historia cyberpunk y combates JRPG como los de " +
                "antes se unen en esta aventura española para PC y consolas ",
            "El equipo de Meridiem Games poco a poco va cogiendo el gusto a la" +
                    " publicación de aventuras independientes a cada cuál más interesante. ",
            "hace 6 horas",
            "https://i.blogs.es/0b240c/3/1366_910.png",
            "https://www.3djuegos.com/juegos/neon-blood/noticias/historia-" +
                    "cyberpunk-combates-jrpg-como-antes-se-unen-esta-aventura-espanola-para-pc-consolas")
        val n8 = Noticia(8,"Uncharted: Legacy of Thieves Collection ya es el lanzamiento " +
                "más flojo de PlayStation en PC ",
            "PlayStation tiene la mirada puesta en el ecosistema de PC, razón por la que " +
                    "entregas como God of War, Marvel’s Spider-Man y Horizon: Zero Dawn ya se " +
                    "encuentran disponibles en ordenadores.",
            "22/10/2022",
            "https://i.blogs.es/cda3df/uncharted-legacy-of-thieves/1366_910.jpeg",
            "https://www.3djuegos.com/juegos/uncharted-legacy-of-thieves-collection/noticias" +
                    "/uncharted-legacy-of-thieves-collection-lanzamiento-flojo-playstation-pc")
        val n9 = Noticia(9,"Ubisoft anuncia la fecha de lanzamiento de Anno 1800" +
                " para PS5, Xbox Series y Amazon Luna ",
            "El título más reciente de la serie Anno llegará a PlayStation, Xbox y Amazon " +
                    "Luna en algún punto de 2023. Ubisoft, la compañía tras el juego de estrategia " +
                    "y simulación, fue la encargada de revelar el futuro más próximo de Anno 1800.",
            "hace 1 hora",
            "https://i.blogs.es/c7b698/anno-1800/1366_910.jpeg",
            "https://www.3djuegos.com/juegos/anno-1800/noticias/" +
                    "ubisoft-anuncia-fecha-lanzamiento-anno-1800-para-playstation-5-xbox-" +
                    "series-amazon-luna")

        val n10 = Noticia(10,"La diferencia gráfica entre realismo y videojuegos cada " +
                "vez se reduce más: así se ve Ámsterdam en MW2 comparado con la vida real ",
            "Con el paso de los años ya sabemos que van saliendo juegos con mejores gráficos " +
                    "y que muchas veces los comparamos con la vida real misma. Este es uno de los " +
                    "casos donde Activision ha logrado con Call of Duty: Modern Warfare II una " +
                    "comparación de lo más fidedigna de la ciudad de Ámsterdam.",
            "10/10/2022",
            "https://i.blogs.es/10d2c2/modern-warfare-2/1366_910.jpeg",
            "https://www.3djuegos.com/juegos/call-of-duty-modern-warfare-2-2022/noticias" +
                    "/diferencia-grafica-realismo-videojuegos-cada-vez-se-reduce-asi-se-ve-amsterdam" +
                    "-modern-warfare-2-comparado-vida-real")


        noticias.add(n1)
        noticias.add(n3)
        noticias.add(n10)
        noticias.add(n9)
        noticias.add(n2)
        noticias.add(n4)
        noticias.add(n5)
        noticias.add(n6)
        noticias.add(n7)
        noticias.add(n8)

        return noticias
    }

}