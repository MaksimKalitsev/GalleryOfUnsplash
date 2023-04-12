package com.example.galleryofunsplash.repository


import com.example.galleryofunsplash.di.AppModule
import com.example.galleryofunsplash.ui.main.GalleryData

interface IPhotoRepository {
    suspend fun getPhoto(): Result<List<GalleryData>>
}

class PhotoRepository(accessKey: String) : IPhotoRepository {
    private val api = AppModule(accessKey).unsplashApi
    override suspend fun getPhoto(): Result<List<GalleryData>> =
        try {
            val photos = api.getPhotos().map { it.toGalleryData() }
            Result.success(photos)
        } catch (e: Exception) {
            Result.failure(e)
        }

}