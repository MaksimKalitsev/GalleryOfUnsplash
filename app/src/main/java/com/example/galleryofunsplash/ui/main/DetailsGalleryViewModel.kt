package com.example.galleryofunsplash.ui.main

import androidx.lifecycle.ViewModel

class DetailsGalleryViewModel : ViewModel() {

    var isInitialized = false
        private set

    fun init() {
        if (isInitialized.not()) {
            isInitialized = true
        }
    }
}