package com.example.travelrecordapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.ActivityAfterSplashBinding
import com.example.travelrecordapp.ui.login.LoginActivity
import com.example.travelrecordapp.ui.register.RegisterActivity

class AfterSplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAfterSplashBinding
    private val viewModel: AfterSplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_TravelRecordApp)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_after_splash)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.apply {
            loginActivityEvent.observe(this@AfterSplashActivity){
                val intent = Intent(this@AfterSplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            registerActivityEvent.observe(this@AfterSplashActivity){
                val intent = Intent(this@AfterSplashActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
        }
    }
}