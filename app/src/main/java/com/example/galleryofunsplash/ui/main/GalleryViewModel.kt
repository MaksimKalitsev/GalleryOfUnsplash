package com.example.galleryofunsplash.ui.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryofunsplash.repository.IPhotoRepository
import com.example.galleryofunsplash.repository.PhotoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GalleryViewModel() : ViewModel() {

    data class State(
        val photos: List<GalleryData>,
        val requestState: RequestState
    )

    private var repository = PhotoRepository(accessKey = "Client-ID wvoLOlxEjI8fbNjlBjTla9_QPllIwqruJqyhQNCAhgA")
    val photosLiveData = MutableLiveData(State(listOf(), RequestState.LOADING))

    var isInitialized = false
        private set

    fun init() {
        if (isInitialized.not()) {
            isInitialized = true
        }
    }

    private val currentState: State
        get() = photosLiveData.value!!
    fun getPhotos() {
        viewModelScope.launch {
            photosLiveData.value = currentState.copy(requestState = RequestState.LOADING)
            delay(1000)
            repository.getPhoto().onSuccess {
                photosLiveData.value = currentState.copy(photos = it, requestState = RequestState.SUCCESS)
            }.onFailure {
                photosLiveData.value = currentState.copy(requestState = RequestState.ERROR)
            }
        }
    }
}