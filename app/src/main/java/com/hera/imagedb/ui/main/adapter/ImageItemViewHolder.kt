package com.hera.imagedb.ui.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hera.imagedb.R
import com.hera.imagedb.data.module.ImageUrl
import com.hera.imagedb.databinding.ItemImageBinding
import com.hera.imagedb.util.ImageLoader

class ImageItemViewHolder(
    private val binding: ItemImageBinding,
    private val imageLoader: ImageLoader,
    private val listener: Listener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(imageUrl: ImageUrl) {
        with(binding) {
            imageLoader.displayImage(imageUrl.url, R.drawable.ic_holder, imageView)
            deleteTextView.setOnClickListener { listener.delete(imageUrl) }
            imageView.setOnClickListener { listener.openFullScreen(imageUrl.url) }
        }
    }

    interface Listener {

        fun delete(imageUrl: ImageUrl)

        fun openFullScreen(url: String)
    }
}