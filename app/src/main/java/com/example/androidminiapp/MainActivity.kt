package com.example.androidminiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidminiapp.ui.adapters.ViewPagerAdapter
import com.example.androidminiapp.databinding.ActivityMainBinding
import com.example.androidminiapp.ui.fragments.Home

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val adapter = ViewPagerAdapter(this)
        binding.viewpagerId.offscreenPageLimit = 1
        val tableNames = arrayOf("Home", "Category")
        adapter.addFragment(Home(), tableNames[0])
        binding.viewpagerId.adapter = adapter

    }
}