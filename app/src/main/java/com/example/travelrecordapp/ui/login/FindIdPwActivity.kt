package com.example.travelrecordapp.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.ActivityFindIdPwBinding

class FindIdPwActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFindIdPwBinding
    private val viewModel: FindPwViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_find_id_pw)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel


    }
}