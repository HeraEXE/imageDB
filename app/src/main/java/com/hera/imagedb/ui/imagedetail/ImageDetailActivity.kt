package com.hera.imagedb.ui.imagedetail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hera.imagedb.R
import com.hera.imagedb.databinding.ActivityImageDetailBinding
import com.hera.imagedb.util.ImageLoader

class ImageDetailActivity : AppCompatActivity() {

    companion object {
        private const val URL = "url"

        fun createIntent(context: Context, url: String): Intent {
            return Intent(context, ImageDetailActivity::class.java)
                .putExtra(URL, url)
        }
    }

    private lateinit var binding: ActivityImageDetailBinding
    private var imageLoader: ImageLoader? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImageDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        displayImage()
    }

    override fun onDestroy() {
        super.onDestroy()
        imageLoader?.clearCache()
    }

    private fun displayImage() {
        val url = intent.getStringExtra(URL)
        url?.let {
            imageLoader = ImageLoader(applicationContext)
            imageLoader?.displayImage(it, R.drawable.ic_holder, binding.imageView)
        } ?: Toast.makeText(this, R.string.url_error, Toast.LENGTH_SHORT).show()
    }
}