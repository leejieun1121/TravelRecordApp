package com.example.travelrecordapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

object RetrofitService {
    private val baseURL = "https://azanghs.cafe24.com/itstudy/"

    var retrofit = Retrofit.Builder().baseUrl(baseURL)
            //response String으로 받아오려고
        .addConverterFactory(ScalarsConverterFactory.create())
            //response Json 으로
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service : RequestInterface = retrofit.create(
        RequestInterface::class.java
    )
}