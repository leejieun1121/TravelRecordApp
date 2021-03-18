package com.example.travelrecordapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.travelrecordapp.MainActivity
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.apply {
            finishEvent.observe(this@LoginActivity){
                finish()
            }
            findPwActivityEvent.observe(this@LoginActivity){
                val intent = Intent(this@LoginActivity, FindIdPwActivity::class.java)
                startActivity(intent)
            }

            loginOkEvent.observe(this@LoginActivity){
                //TODO 로그인 서버처리
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}