package com.example.travelrecordapp.ui.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.ActivityDetailBinding
import com.example.travelrecordapp.databinding.ActivityRegisterBinding
import com.example.travelrecordapp.ui.register.RegisterViewModel

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val intent = intent
        Log.d("넘겨받은 데이터~!~!",intent.getSerializableExtra("detailTourData").toString())

        //TODO 레이아웃 똑같아서 Media Activity 하나로 합침 케이스 나눠서 item 넘겨줘야할듯!
        binding.btnPlayVideo.setOnClickListener {
            val intent = Intent(this,MediaActivity::class.java)
            startActivity(intent)
        }

        binding.btnPlayAudio.setOnClickListener {
            val intent = Intent(this,MediaActivity::class.java)
            startActivity(intent)
        }

        viewModel.apply {
            finishEvent.observe(this@DetailActivity,{
                finish()
            })
        }

    }
}