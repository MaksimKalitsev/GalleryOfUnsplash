package com.example.galleryofunsplash.ui.main

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.galleryofunsplash.R
import com.example.galleryofunsplash.databinding.FragmentGalleryBinding


enum class RequestState {
    LOADING, SUCCESS, ERROR
}

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<GalleryViewModel>()
    private lateinit var adapter: GalleryAdapter

    private var columnCount = 2

    private val callback = object : GalleryAdapter.GalleryHolder.Callback {
        override fun onPictureClicked(item: GalleryData) {

            val direction =
                GalleryFragmentDirections.actionGalleryFragmentToDetailsGalleryFragment(galleryData = item)
            findNavController().navigate(direction)
        }
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

        adapter = GalleryAdapter(callback)
        val layoutManager = StaggeredGridLayoutManager(
            2,
            StaggeredGridLayoutManager.VERTICAL
        )
        binding.rvGallery.layoutManager = layoutManager
        binding.rvGallery.adapter = adapter

        viewModel.photosLiveData.observe(viewLifecycleOwner, stateObserver)
        viewModel.getPhotos()

        setupToolbarMenu()


    }

    private fun setupToolbarMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object : MenuProvider {
//            override fun onPrepareMenu(menu: Menu) {
//                // Handle for example visibility of menu items
//            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                setColumnCount()
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    private fun setColumnCount() {
        columnCount++
        if (columnCount > 4) {
            columnCount = 2
        }
        val layoutManager =
            StaggeredGridLayoutManager(columnCount, StaggeredGridLayoutManager.VERTICAL)
        binding.rvGallery.layoutManager = layoutManager
    }


    private val stateObserver = Observer<GalleryViewModel.State> { state ->
        binding.progressBar.isVisible = state.requestState == RequestState.LOADING
        when (state.requestState) {
            RequestState.SUCCESS -> {
                adapter.items = state.photos
//                adapter.updateItems(state.photos)
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
