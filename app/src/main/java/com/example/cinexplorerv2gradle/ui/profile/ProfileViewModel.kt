package com.example.cinexplorerv2gradle.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is PHOTO Fragment"
    }
    val text: LiveData<String> = _text
}