package com.example.galleryofunsplash.repository

import com.example.galleryofunsplash.models.Photo

interface IPhotoRepository {
    suspend fun getPhoto(accessKey: String): Result<List<Photo>>
}

class PhotoRepository(private val api: UnsplashApi) : IPhotoRepository {

    override suspend fun getPhoto(accessKey: String): Result<List<Photo>> =
        try {
            val photos = api.getPhotos(accessKey)
            Result.success(photos)
        } catch (e: Exception) {
            Result.failure(e)
        }

}