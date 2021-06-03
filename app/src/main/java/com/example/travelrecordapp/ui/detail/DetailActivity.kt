package com.example.travelrecordapp.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.AudioInfo
import com.example.travelrecordapp.data.DetailTourData
import com.example.travelrecordapp.data.EventObserver
import com.example.travelrecordapp.databinding.ActivityDetailBinding
import com.example.travelrecordapp.databinding.ActivityRegisterBinding
import com.example.travelrecordapp.ui.media.MediaActivity
import com.example.travelrecordapp.ui.register.RegisterViewModel
import java.io.Serializable

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    private lateinit var viewModelFactory : DetailViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        viewModelFactory= DetailViewModelFactory(intent.getSerializableExtra("detailTourData") as DetailTourData)
        binding.viewModel = ViewModelProvider(this,viewModelFactory).get(DetailViewModel::class.java)

        //TODO 레이아웃 똑같아서 Media Activity 하나로 합침 케이스 나눠서 item 넘겨줘야할듯!
        binding.btnPlayVideo.setOnClickListener {
            val intent = Intent(this, MediaActivity::class.java)
            intent.putExtra("audioList",viewModel.audioList as Serializable)
            startActivity(intent)
        }

        binding.btnPlayAudio.setOnClickListener {
            val intent = Intent(this, MediaActivity::class.java)
            intent.putExtra("videoList",viewModel.videoList as Serializable)
            startActivity(intent)
        }

        viewModel.apply {
            finishEvent.observe(this@DetailActivity, EventObserver{
                finish()
            })
        }

    }
}