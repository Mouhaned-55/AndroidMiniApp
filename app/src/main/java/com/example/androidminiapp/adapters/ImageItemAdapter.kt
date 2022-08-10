package com.example.androidminiapp.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.enigmaticdevs.wallpaperapp.models.Photo
import com.example.androidminiapp.R
import java.time.format.DateTimeFormatter

class ImageItemAdapter(private val photos: MutableList<Photo>, private val context: Context) :
    RecyclerView.Adapter<ImageItemAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photo: ImageView = itemView.findViewById(R.id.photo)
        val username: TextView = itemView.findViewById(R.id.username)
        val created_at: TextView = itemView.findViewById(R.id.created_at)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(context)
            .load(photos[position].url.regular)
            .placeholder(ColorDrawable(Color.parseColor(photos[position].color)))
            .into(holder.photo)
        holder.username.text = photos[position].user.username.toString()
        holder.created_at.text = photos[position].created_at.toString()
    }

    override fun getItemCount(): Int {
        return photos.size
    }

}