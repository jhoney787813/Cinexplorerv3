package com.example.cinexplorerv2gradle.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VideoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is VIDEO Fragment"
    }
    val text: LiveData<String> = _text
}