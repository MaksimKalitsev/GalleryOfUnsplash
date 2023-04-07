package com.example.galleryofunsplash.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.galleryofunsplash.R
import com.example.galleryofunsplash.databinding.ItemPhotoBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {

    private val photosList = ArrayList<GalleryData>()

    class GalleryHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemPhotoBinding.bind(item)
        fun bind(data: GalleryData) = with(binding) {

            //TODO something
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return GalleryHolder(view)
    }

    override fun getItemCount(): Int {
        return photosList.size
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bind(photosList[position])
    }

}