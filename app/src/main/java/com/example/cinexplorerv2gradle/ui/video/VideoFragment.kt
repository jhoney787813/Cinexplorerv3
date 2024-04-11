package com.example.cinexplorerv2gradle.ui.video

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinexplorerv2gradle.R
import com.example.cinexplorerv2gradle.databinding.FragmentGalleryBinding
import com.example.cinexplorerv2gradle.databinding.FragmentVideoBinding
import com.example.cinexplorerv2gradle.ui.photo.Movie
import android.widget.MediaController

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var videoView: VideoView
    private lateinit var buttonPlayPause: ImageButton
    private lateinit var buttonStop: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(VideoViewModel::class.java)

        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        showMovie(binding.root.context);
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showMovie(context:  android.content.Context) {
        videoView = binding.videoView
        buttonPlayPause = binding.buttonPlayPause
        buttonStop = binding.buttonStop

        val videoPath = "android.resource://" + context.packageName + "/" + R.raw.video1
        val videoUri = Uri.parse(videoPath)
        videoView.setVideoURI(videoUri)
        val mediaController = MediaController(context)
        mediaController.setAnchorView(videoView)
        videoView.setMediaController(mediaController)

        buttonPlayPause.setOnClickListener {
            if (videoView.isPlaying) {
                videoView.pause()
                buttonPlayPause.setImageResource(R.drawable.play)
            } else {
                videoView.start()
                buttonPlayPause.setImageResource(R.drawable.playpause)
            }
        }
        buttonStop.setOnClickListener {
            videoView.stopPlayback()
            buttonPlayPause.setImageResource(R.drawable.play)
        }
    }
}