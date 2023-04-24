package com.example.galleryofunsplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.galleryofunsplash.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}