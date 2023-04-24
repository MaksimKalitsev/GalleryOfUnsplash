package com.example.galleryofunsplash.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.galleryofunsplash.databinding.ItemPhotoBinding


class GalleryAdapter(private val callback: GalleryHolder.Callback) : RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {


   var items: List<GalleryData> = emptyList()
      set(newValue) {
          val diffCallback = DiffCallback(field, newValue)
          val diffResult = DiffUtil.calculateDiff(diffCallback)
          field = newValue
          diffResult.dispatchUpdatesTo(this)
      }

//    fun updateItems(items: List<GalleryData>) {
//        this.items = items
//        notifyDataSetChanged()
//    }

    class GalleryHolder(private val binding: ItemPhotoBinding, private val callback: Callback) : RecyclerView.ViewHolder(binding.root) {

        interface Callback {
            fun onPictureClicked(item: GalleryData)
        }

        val mConstraintLayout: ConstraintLayout = binding.constraint
        val mPhoto: ImageView = binding.ivPhoto


        fun bind(data: GalleryData) = with(binding) {
            ivPhoto.load(data.photo)
            tvDescription.text = data.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPhotoBinding.inflate(inflater, parent, false)
        return GalleryHolder(binding, callback)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
//        holder.bind(items[position])
        val test = items[position]
        holder.bind(test)
        val set = ConstraintSet()

        val ratio = String.format("%d:%d", test.width, test.height)
        set.clone(holder.mConstraintLayout)
        set.setDimensionRatio(holder.mPhoto.id, ratio)
        set.applyTo(holder.mConstraintLayout)


        holder.mPhoto.setOnClickListener {
            callback.onPictureClicked(test)
//            val navController = Navigation.findNavController(it)
//            navController.navigate(R.id.action_galleryFragment_to_detailsGalleryFragment)
        }
    }

}