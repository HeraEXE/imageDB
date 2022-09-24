package com.hera.imagedb.ui.addurl

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hera.imagedb.data.repository.ImageUrlRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUrlViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val imageUrlRepository: ImageUrlRepository
) : ViewModel() {

    companion object {
        private const val URL = "url"
    }

    private var _url: String
    get() = savedStateHandle[URL] ?: ""
    set(value) { savedStateHandle[URL] = value }
    val url: String get() = _url

    fun updateUrl(url: CharSequence?) {
        _url = if (url.isNullOrEmpty()) "" else url.toString()
    }

    fun addUrl() {
        viewModelScope.launch {
            imageUrlRepository.addImageUrl(url)
        }
    }
}