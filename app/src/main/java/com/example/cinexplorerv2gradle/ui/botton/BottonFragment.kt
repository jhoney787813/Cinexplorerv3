package com.example.cinexplorerv2gradle.ui.botton

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cinexplorerv2gradle.R
import com.example.cinexplorerv2gradle.databinding.FragmentBottonBinding
import com.example.cinexplorerv2gradle.databinding.FragmentGalleryBinding
import com.google.android.material.snackbar.Snackbar

class BottonFragment : Fragment() {

    private var _binding: FragmentBottonBinding? = null
    private var suma:Int = 0
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(BottonViewModel::class.java)

        _binding = FragmentBottonBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textBotton
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.btn1.setOnClickListener {view->
            suma=suma+1
            showSum(view)
        }

        binding.btn2.setOnClickListener {view->
            suma=suma+2
            showSum(view)
        }

        binding.btn3.setOnClickListener {view->
            suma=suma+3
            showSum(view)
        }

        binding.btn4.setOnClickListener {view->
            suma=suma+4
            showSum(view)
        }

        binding.btn5.setOnClickListener {view->
            suma=suma+5
            showSum(view)
        }

        binding.btn6.setOnClickListener {view->
            suma=suma+6
            showSum(view)
        }



        return root
    }
    fun showSum(v:View) {
        Snackbar.make(v, "SUMA ACUMULATIVA="+this.suma, Snackbar.LENGTH_LONG)
            .setAction("Action", null)
            .setAnchorView(R.id.fab).show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}