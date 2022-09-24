package com.hera.imagedb.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hera.imagedb.data.module.ImageUrl
import com.hera.imagedb.data.repository.ImageUrlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val imgUrlRepository: ImageUrlRepository
) : ViewModel() {

    private val _imgUrls by lazy { MutableStateFlow<List<ImageUrl>?>(null) }
    val imgUrls: StateFlow<List<ImageUrl>?> get() = _imgUrls

    fun getAllImageUrls() {
        viewModelScope.launch(Dispatchers.IO) {
            imgUrlRepository.getAllImageUrls().collect { imgUrls ->
                _imgUrls.value = imgUrls
            }
        }
    }

    fun deleteImageUrl(imageUrl: ImageUrl) {
        viewModelScope.launch(Dispatchers.IO) {
            imgUrlRepository.deleteImageUrl(imageUrl)
        }
    }
}