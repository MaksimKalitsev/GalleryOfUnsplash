package com.example.galleryofunsplash.models

import android.os.Parcelable
import com.example.galleryofunsplash.ui.main.GalleryData
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Photo(
    val id: String,
    val width: Int,
    val height: Int,
    @SerializedName("alt_description")
    val description: String,
    val urls: Urls
) : Parcelable {
    @Parcelize
    data class Urls(
        @SerializedName("full")
        val full_size: String,
        @SerializedName("thumb")
        val thumb_size: String
    ) : Parcelable

    fun toGalleryData(): GalleryData =
        GalleryData(
            id = id,
            photo = urls.thumb_size,
            description = description
        )

}


