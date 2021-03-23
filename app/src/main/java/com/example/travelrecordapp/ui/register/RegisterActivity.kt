package com.example.travelrecordapp.ui.register

import android.bluetooth.BluetoothClass
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.NetworkResponse
import com.example.travelrecordapp.data.RequestRegister
import com.example.travelrecordapp.data.ResponseRegister
import com.example.travelrecordapp.data.RetrofitService
import com.example.travelrecordapp.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        //TODO DataSource로 넘기기
        viewModel.apply {
            finishEvent.observe(this@RegisterActivity){
                finish()
            }
            registerOkEvent.observe(this@RegisterActivity) {
                val user = RequestRegister("test@naver.com", "1234", "N", "testtoken", "nick")
                RetrofitService.service.register(user).enqueue(object : Callback<ResponseRegister>{
                    override fun onResponse(
                        call: Call<ResponseRegister>,
                        response: Response<ResponseRegister>
                    ) {
//                       Log.d("tagRegister",response.body()!!.message)
                    }

                    override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
                        Log.d("tagRegister",t.localizedMessage)
                    }
                })

//                when(response){
//                    is NetworkResponse.Success -> {
//                       Log.d("tag","t1")
//                    }
//                    is NetworkResponse.ApiError -> {
//                        Log.d("tag","t2")
////                        return NetworkResponse.ApiError(response.body, response.code)
//                    }
//                    is NetworkResponse.NetworkError -> {
//                        Log.d("tag","t3")
////                        return NetworkResponse.NetworkError(response.error)
//                    }
//                    is NetworkResponse.UnknownError -> {
//                        Log.d("tag","t4")
////                        return NetworkResponse.UnknownError(response.error)
//                    }
//                }
            }

        }
    }
}