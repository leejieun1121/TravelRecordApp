package com.example.travelrecordapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.travelrecordapp.MainActivity
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.*
import com.example.travelrecordapp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.apply {
            finishEvent.observe(this@LoginActivity,EventObserver{
                finish()
            })
            findPwActivityEvent.observe(this@LoginActivity,EventObserver{
                val intent = Intent(this@LoginActivity, FindIdPwActivity::class.java)
                startActivity(intent)
            })

            userData.observe(this@LoginActivity){
                //TODO it.token  넘기기
                if(it!=null){
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                }
            }

            toastMessage.observe(this@LoginActivity){
                Toast.makeText(this@LoginActivity,it,Toast.LENGTH_SHORT).show()
            }
        }

    }
}