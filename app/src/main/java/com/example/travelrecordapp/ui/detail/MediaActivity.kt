package com.example.travelrecordapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.ActivityDetailBinding
import com.example.travelrecordapp.databinding.ActivityMediaBinding

class MediaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMediaBinding
    private val viewModel: MediaViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_media)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.apply {
            finishEvent.observe(this@MediaActivity, {
              finish()
            })
        }


    }
}