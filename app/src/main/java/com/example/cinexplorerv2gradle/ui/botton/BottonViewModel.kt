package com.example.cinexplorerv2gradle.ui.botton

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BottonViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is BOTTON Fragment"
    }
    val text: LiveData<String> = _text
}