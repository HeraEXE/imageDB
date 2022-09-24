package com.hera.imagedb.ui.addurl

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import com.hera.imagedb.databinding.ActivityAddUrlBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUrlActivity : AppCompatActivity() {

    companion object {
        fun createIntent(context: Context) = Intent(context, AddUrlActivity::class.java)
    }

    private lateinit var binding: ActivityAddUrlBinding
    private val viewModel: AddUrlViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUrlBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding) {
            if (savedInstanceState != null && urlEditText.text.toString() != viewModel.url) {
                urlEditText.setText(viewModel.url)
            }
            urlEditText.doOnTextChanged { text, _, _, _ ->
                viewModel.updateUrl(text)
            }
            addButton.setOnClickListener {
                viewModel.addUrl()
                finish()
            }
        }
    }
}