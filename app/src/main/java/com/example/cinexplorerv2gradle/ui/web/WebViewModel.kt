package com.example.cinexplorerv2gradle.ui.web

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WebViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is WEB Fragment"
    }
    val text: LiveData<String> = _text
}