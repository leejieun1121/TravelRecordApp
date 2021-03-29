package com.example.travelrecordapp.ui.register

import android.bluetooth.BluetoothClass
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
            emptyWarning.observe(this@RegisterActivity){
                Toast.makeText(this@RegisterActivity,"입력하지 않은 곳이 있습니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }
}