package com.walktalk.stride.presentation.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.walktalk.stride.data.repository.MainRepository

class MainViewModel : ViewModel() {
    private val mainRepository = MainRepository()

    init {
        Log.d("MainViewModel", "MainViewModel created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("MainViewModel", "MainViewModel destroyed")
    }
}