package com.example.galleryofunsplash.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.galleryofunsplash.R
import com.example.galleryofunsplash.databinding.ItemPhotoBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {

    private val set = ConstraintSet()


   var items: List<GalleryData> = emptyList()
      set(newValue) {
          val diffCallback = DiffCallback(field, newValue)
          val diffResult = DiffUtil.calculateDiff(diffCallback)
          field = newValue
          diffResult.dispatchUpdatesTo(this)
      }

    class GalleryHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ItemPhotoBinding.bind(item)

        val mConstraintLayout: ConstraintLayout = item.findViewById(R.id.constraint)
        val mPhoto: ImageView = item.findViewById(R.id.iv_photo)
        val mDescription: TextView = item.findViewById(R.id.tv_description)

        fun bind(data: GalleryData) = with(binding) {
            ivPhoto.load(data.photo)
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
        val test = items[position]
        holder.bind(test)


        val ratio = String.format("%d:%d", test.width, test.height)
        set.clone(holder.mConstraintLayout)
        set.setDimensionRatio(holder.mPhoto.id, ratio)
        set.applyTo(holder.mConstraintLayout)
    }

}