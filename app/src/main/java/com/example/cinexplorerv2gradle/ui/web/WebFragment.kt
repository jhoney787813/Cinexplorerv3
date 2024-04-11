package com.example.cinexplorerv2gradle.ui.web

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinexplorerv2gradle.R
import com.example.cinexplorerv2gradle.databinding.FragmentGalleryBinding
import com.example.cinexplorerv2gradle.databinding.FragmentWebBinding
import com.google.android.material.snackbar.Snackbar

class WebFragment : Fragment() {

    private var _binding: FragmentWebBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var webView: WebView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(WebViewModel::class.java)

        _binding = FragmentWebBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textWeb
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        initWebView()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun initWebView() {


        webView = binding.webView

        // Habilitar JavaScript para el WebView
        webView.settings.javaScriptEnabled = true

        // Cargar una URL inicial (puedes cambiarla si lo deseas)
        val initialUrl = "https://www.google.com"
        webView.loadUrl(initialUrl)

        webView.webViewClient = WebViewClient()

        // Configurar los clics de los botones
        binding.buttonBack.setOnClickListener {
            if (webView.canGoBack()) {
                webView.goBack()
            }
        }

        binding.buttonForward.setOnClickListener {
            if (webView.canGoForward()) {
                webView.goForward()
            }
        }

        binding.buttonSearch.setOnClickListener {

            var urlsearch= binding.editTextUrl.text
            if(urlsearch.isNullOrBlank()||urlsearch.isNullOrEmpty())
                 webView.loadUrl(urlsearch.toString())
            else
                Snackbar.make(this.rootView, "Ejemplo de botton llamando un toast", Snackbar.LENGTH_LONG)
                    .setAction("Action", null)
                    .setAnchorView(R.id.fab).show()
        }
    }
}