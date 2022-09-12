package com.example.androidminiapp.ui.popups

import android.app.Dialog
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import com.bumptech.glide.Glide
import com.example.androidminiapp.models.PhotoUrl
import com.example.androidminiapp.databinding.StoryPopupBinding

class StoryPopUp(superContext: Context,
                 private val homeImage: PhotoUrl)
    : Dialog(superContext) {
    private lateinit var binding: StoryPopupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = StoryPopupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupComponents()

        //Close the dialog after 3s
        Handler(Looper.getMainLooper()).postDelayed({

            this.dismiss()

        }, 10000)
    }

    private fun setupComponents() {
        // display the popup components

        Glide
            .with(context)
            .load(homeImage.full)
            .centerInside()
            .into(binding.storyPopupImage)

    }
}