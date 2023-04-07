package com.example.galleryofunsplash.models

import android.os.Parcelable
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
) : Parcelable{
    @Parcelize
    data class Urls(
        @SerializedName("fullsize")
        val full: String,
        @SerializedName("thumbsize")
        val thumb: String
    ) : Parcelable
}


