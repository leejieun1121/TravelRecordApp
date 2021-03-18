package com.example.travelrecordapp.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.apply {
            registerOkEvent.observe(this@RegisterActivity){
                //TODO 회원가입 서버
                finish()
            }
        }
    }
}