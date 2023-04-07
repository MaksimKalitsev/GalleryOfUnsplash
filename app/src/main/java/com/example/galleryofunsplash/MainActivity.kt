package com.example.galleryofunsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.galleryofunsplash.databinding.ActivityMainBinding
import com.example.galleryofunsplash.ui.main.GalleryFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, GalleryFragment.newInstance())
                .commitNow()
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}