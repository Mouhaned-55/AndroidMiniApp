package com.example.androidminiapp.ui.adapters

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidminiapp.R
import com.example.androidminiapp.databinding.StoryItemBinding
import com.example.androidminiapp.models.PhotoUi

open class StoryItemAdapter(private val context: Context, private val list: ArrayList<PhotoUi>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: StoryItemBinding
    private var onClickListener: OnClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(
            StoryItemBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val model = list[position]

        if (holder is MyViewHolder) {
            holder.binding.username.text = model.user!!.username

            if (!model.Clicked) {
                holder.binding.StoryView.setBackgroundResource(
                    R.drawable.custom_bg
                )
            } else {
                holder.binding.StoryView.setBackgroundResource(
                    R.drawable.custom_bg_grey
                )
            }

            Glide.with(context).load(model.user.profileImage.large)
                .placeholder(ColorDrawable(Color.parseColor(model.color)))
                .into(holder.binding.imageStory)

            holder.itemView.setOnClickListener {
                if (onClickListener != null) {
                    onClickListener!!.onClick(position, model)
                }
            }

        }


    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface OnClickListener {
        fun onClick(position: Int, model: PhotoUi)
    }

    private class MyViewHolder(binding: StoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding = binding
    }

}

