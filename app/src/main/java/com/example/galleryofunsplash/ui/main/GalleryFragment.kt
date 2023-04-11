package com.example.galleryofunsplash.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.galleryofunsplash.databinding.FragmentGalleryBinding


enum class RequestState {
    LOADING, SUCCESS, ERROR
}

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<GalleryViewModel>()
    private val adapter = GalleryAdapter()

    companion object {
        fun newInstance() = GalleryFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (viewModel.isInitialized.not())
            viewModel.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.photosLiveData.observe(viewLifecycleOwner, progressObserver)
        _binding = FragmentGalleryBinding.bind(view)

        viewModel.getPhotos()
    }

    private val progressObserver = Observer<GalleryViewModel.State> {
        binding.progressBar.isVisible = it.requestState == RequestState.LOADING
        when (it.requestState) {
            RequestState.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
                //todo
            }
            RequestState.ERROR -> {
                //todo
            }
            else -> {
                //todo
            }
        }
    }
}
