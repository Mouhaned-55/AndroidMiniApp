package com.example.androidminiapp.models

import com.enigmaticdevs.wallpaperapp.models.Exif
import com.enigmaticdevs.wallpaperapp.models.User
import java.util.*

data class PhotoUi(
    val id: String? = null,
    val width: String? = null,
    val height: String? = null,
    val created_at: Date? = null,
    val color: String? = null,
    val description: String? = null,
    val url: PhotoUrl? = null,
    val user: User? = null,
    val likes: String? = null,
    val sponsorship: Sponsorship? = null,
    val exif: Exif? = null,
    var Clicked: Boolean = false,
    var seen : Boolean = false

)
