package com.hera.imagedb.ui.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.hera.imagedb.R
import com.hera.imagedb.data.module.ImageUrl
import com.hera.imagedb.databinding.ActivityMainBinding
import com.hera.imagedb.ui.addurl.AddUrlActivity
import com.hera.imagedb.ui.main.adapter.ImageAdapter
import com.hera.imagedb.ui.main.adapter.ImageItemViewHolder
import com.hera.imagedb.ui.imagedetail.ImageDetailActivity
import com.hera.imagedb.util.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var imageAdapter: ImageAdapter
    private lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupImageAdapter()
        initView()
        setupImgUrlsObserver()
        checkPermissions()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.activity_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem) = when(item.itemId) {
        R.id.add_action -> {
            val intent = AddUrlActivity.createIntent(this)
            startActivity(intent)
            true
        }
        else -> false
    }

    override fun onDestroy() {
        super.onDestroy()
        imageLoader.clearCache()
    }

    private fun setupImageAdapter() {
        imageLoader = ImageLoader(applicationContext)
        val listener = object : ImageItemViewHolder.Listener {
            override fun delete(imageUrl: ImageUrl) {
                viewModel.deleteImageUrl(imageUrl)
            }

            override fun openFullScreen(url: String) {
                val intent = ImageDetailActivity.createIntent(this@MainActivity, url)
                startActivity(intent)
            }
        }
        imageAdapter = ImageAdapter(imageLoader, listener)
    }

    private fun initView() {
        binding.imageRecyclerView.adapter = imageAdapter
    }

    private fun checkPermissions() {
        val requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                if (isGranted) {
                    viewModel.getAllImageUrls()
                }
                else {
                    Toast.makeText(this, R.string.permissions_denied, Toast.LENGTH_SHORT).show()
                }
            }
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestPermissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        else {
            viewModel.getAllImageUrls()
        }
    }

    private fun setupImgUrlsObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.imgUrls.collect { imageUrls ->
                    imageUrls?.let { imageAdapter.submitList(it) }
                }
            }
        }
    }
}