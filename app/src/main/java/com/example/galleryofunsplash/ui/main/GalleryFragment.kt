package com.example.galleryofunsplash.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.galleryofunsplash.databinding.FragmentGalleryBinding


enum class RequestState {
    LOADING, SUCCESS, ERROR
}

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<GalleryViewModel>()
    private lateinit var adapter: GalleryAdapter

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

        _binding = FragmentGalleryBinding.bind(view)

        adapter = GalleryAdapter()
        val layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)//GridLayoutManager(requireContext(), 2)
        binding.rvGallery.layoutManager = layoutManager
        binding.rvGallery.adapter = adapter

        viewModel.photosLiveData.observe(viewLifecycleOwner, stateObserver)
        viewModel.getPhotos()

    }

    private val stateObserver = Observer<GalleryViewModel.State> { state ->
        binding.progressBar.isVisible = state.requestState == RequestState.LOADING
        when (state.requestState) {
            RequestState.SUCCESS -> {
                state.photos
                adapter.items = state.photos

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
