package com.example.cinexplorerv2gradle.ui.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinexplorerv2gradle.R
import com.example.cinexplorerv2gradle.databinding.FragmentGalleryBinding
import com.example.cinexplorerv2gradle.databinding.FragmentPhotoBinding

class PhotoFragment : Fragment() {

    private var _binding: FragmentPhotoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(PhotoViewModel::class.java)

        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPhoto
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val listViewMovies: ListView = binding.listViewMovies
        val movies = arrayOf(
            Movie("Shōgun (2024)", "Ambientada en el Japón de 1600, lord Yoshii Toranaga lucha por su vida mientras que sus enemigos en el Consejo de regentes se alían contra él cuando un misterioso barco europeo aparece abandonado cerca de un pueblo pesquero.",R.drawable.shogun),
            Movie("Kung Fu Panda 4 (2024)", "Po se prepara para ser el líder espiritual del Valle de la Paz, buscando un sucesor como Guerrero Dragón. ",R.drawable.kfp4),
            Movie("Fallout (2024)", "Basado en una de las sagas de videojuegos más importantes de todos los tiempos, Fallout es la historia de los que tienen y de los que no tienen en un mundo en el que prácticamente no queda nada para tener.",R.drawable.fallout),
            Movie("Dune: Parte dos (2024)", "Sigue el viaje mítico de Paul Atreides mientras se une a Chani y los Fremen en una guerra de venganza contra los conspiradores que destruyeron a su familia.",R.drawable.dunep2),
            Movie("Padre made in USA (2005)", "Padre made in USA, es una serie de televisión de animación para adolescentes y adultos del creador de Padre de familia, Seth MacFarlane.",R.drawable.padre),
            Movie("Supervixens (1975)", "Clint trabaja en una gasolinera y tiene un irresistible atractivo para las mujeres. ",R.drawable.superv),
            Movie("Chicas malas (2004)", "Una joven adolescente, Cady, acostumbrada a vivir en África con sus padres, zoólogos, se encuentra una nueva jungla cuando se muda a Illinois.",R.drawable.chicasmalas),
            // Agrega más películas según sea necesario

        )

        // Adaptador personalizado para la lista de películas
        //val adapter = ArrayAdapter(this, R.layout.item_movie, R.id.textViewTitle, movies)
        val adapter = ArrayAdapter(binding.root.context,R.layout.item_movie, R.id.textViewTitle, movies)
        listViewMovies.adapter = adapter
        // Manejar clics en los elementos de la lista
        listViewMovies.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val movie = movies[position]
                // Mostrar la descripción de la película seleccionada
               showMovieDescription(this.binding.listViewMovies ,movie,position)
            }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showMovieDescription(v:android.widget.ListView ,movie: Movie,position:Int,) {

        var viewItem =v.get(position)
        val description =  viewItem.findViewById<TextView>(R.id.textViewDescription)
        val image =  viewItem.findViewById<ImageView>(R.id.imageViewPoster)
        description.text = movie.description
        image.setImageResource(movie.imageResId)
    }
}


data class Movie(val title: String, val description: String,val imageResId: Int) {
    override fun toString(): String {
        return title
    }
}