package com.hera.imagedb.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.hera.imagedb.data.module.ImageUrl
import com.hera.imagedb.databinding.ItemImageBinding
import com.hera.imagedb.util.ImageLoader

class ImageAdapter(
    private val imageLoader: ImageLoader,
    private val listener: ImageItemViewHolder.Listener
    ) : ListAdapter<ImageUrl, ImageItemViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<ImageUrl>() {
        override fun areItemsTheSame(oldItem: ImageUrl, newItem: ImageUrl) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ImageUrl, newItem: ImageUrl) = oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemImageBinding.inflate(inflater, parent, false)
        return ImageItemViewHolder(binding, imageLoader, listener)
    }

    override fun onBindViewHolder(holder: ImageItemViewHolder, position: Int) {
        val imageUrl = currentList[position]
        holder.bind(imageUrl)
    }
}