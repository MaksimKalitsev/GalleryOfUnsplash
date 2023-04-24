package com.example.galleryofunsplash.ui.main

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GalleryData(
    val id: String,
    val photo: String,
    val width: Int,
    val height: Int,
    val description: String):Parcelable



