package com.example.travelrecordapp.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.travelrecordapp.MainActivity
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.*
import com.example.travelrecordapp.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                //TODO DataSource로 넘기기

                val user = RequestLogin("test@naver.com", "1234")
                RetrofitService.service.login(user).enqueue(object : Callback<ResponseLogin> {
                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                    ) {
//                        Log.d("tagLogin",response.body()!!.data.token)
                    }

                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        Log.d("tagLogin",t.localizedMessage)
                    }
                })
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }
}