package com.example.androidminiapp.ui.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.androidminiapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

    }

    @SuppressLint("SetTextI18n")
    private fun initRecyclerView() {

        val username = intent.getStringExtra("username");
        val description = intent.getStringExtra("description");
        val photo = intent.getStringExtra("photo")
        val profileImage = intent.getStringExtra("profile_image")
        val created = intent.getStringExtra("created_at")
        val updated = intent.getStringExtra("updated_at")

        binding.name.text = username
        binding.descriptionPopup.text = description
        binding.createdAt.text = "Created at : $created"
        binding.updatedAt.text = "Updated at : $updated"
        Glide.with(applicationContext)
            .load(photo)
            .into(binding.photo)

        Glide.with(applicationContext)
            .load(profileImage)
            .into(binding.profileImage)

        binding.returnButton.setOnClickListener { finish() }

    }

}
