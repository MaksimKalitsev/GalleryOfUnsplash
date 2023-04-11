package com.example.galleryofunsplash.ui.main


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryofunsplash.models.Photo
import com.example.galleryofunsplash.repository.IPhotoRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GalleryViewModel() : ViewModel() {

    data class State (
        val photos: List<Photo>,
        val requestState: RequestState
    )

    private lateinit var repository: IPhotoRepository
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
            repository.getPhoto("wvoLOlxEjI8fbNjlBjTla9_QPllIwqruJqyhQNCAhgA").onSuccess {
                photosLiveData.value = currentState.copy(photos = it, requestState = RequestState.SUCCESS)
            }.onFailure {
                photosLiveData.value = currentState.copy(requestState = RequestState.ERROR)
            }
        }
    }
}