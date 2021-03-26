package com.example.travelrecordapp.data.source.remote

import android.util.Log
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.*
import com.example.travelrecordapp.data.repository.AuthRepository
import com.example.travelrecordapp.data.source.AuthDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRemoteDataSource : AuthDataSource{
    override fun requestLogin(user : RequestLogin, callback: AuthRepository.GetDataCallback<ResponseLogin>) {

        RetrofitService.service.login(user).enqueue(object : Callback<ResponseLogin>{
            override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                if(response.isSuccessful){
                    callback.onSuccess(response.body())
                }
            }

            override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                callback.onFailure(t)
            }

        })
    }
}