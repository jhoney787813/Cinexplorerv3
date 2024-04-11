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
            Movie("Movie 1", "Description 1",R.drawable.web),
            Movie("Movie 2", "Description 2",R.drawable.button),
            Movie("Movie 3", "Description 3",R.drawable.explorer2)
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