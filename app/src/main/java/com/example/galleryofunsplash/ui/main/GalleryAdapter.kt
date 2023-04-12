package com.example.galleryofunsplash.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.galleryofunsplash.R
import com.example.galleryofunsplash.databinding.ItemPhotoBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {

   var items: List<GalleryData> = emptyList()
      set(newValue) {
          val diffCallback = DiffCallback(field, newValue)
          val diffResult = DiffUtil.calculateDiff(diffCallback)
          field = newValue
          diffResult.dispatchUpdatesTo(this)
      }

    class GalleryHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemPhotoBinding.bind(item)
        fun bind(data: GalleryData) = with(binding) {
            ivPhoto.load(data.photo)
          //  ivPhoto.setImageResource(data.photo)
            tvDescription.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return GalleryHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.bind(items[position])
    }

}