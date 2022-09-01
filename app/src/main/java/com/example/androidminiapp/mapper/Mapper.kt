package com.example.androidminiapp.mapper

import com.enigmaticdevs.wallpaperapp.models.*
import com.example.androidminiapp.models.PhotoUi

fun Photo.toPhotoUi(): PhotoUi {
    return PhotoUi(
        id = id,
        width = width,
        height = height,
        created_at = created_at,
        color = color,
        description = description,
        url = url,
        user = user,
        likes = likes,
        sponsorship = sponsorship,
        exif = exif,
    )
}