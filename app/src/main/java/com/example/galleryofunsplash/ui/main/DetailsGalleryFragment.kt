package com.example.galleryofunsplash.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.galleryofunsplash.R
import com.example.galleryofunsplash.databinding.FragmentDetailsGalleryBinding
import com.example.galleryofunsplash.databinding.FragmentGalleryBinding

class DetailsGalleryFragment : Fragment() {

    private var _binding: FragmentDetailsGalleryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailsGalleryViewModel>()
    private lateinit var adapter: GalleryAdapter
    companion object {
        fun newInstance() = DetailsGalleryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.isInitialized.not())
            viewModel.init()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailsGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentDetailsGalleryBinding.bind(view)
    }



}