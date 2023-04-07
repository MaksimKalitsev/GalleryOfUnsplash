package com.example.galleryofunsplash.repository

import com.example.galleryofunsplash.models.Photo
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun getPhotos(
        @Header("Client-ID") accessKey: String,
    ): List<Photo>
}