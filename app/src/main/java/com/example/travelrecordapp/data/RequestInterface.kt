package com.example.travelrecordapp.data

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface RequestInterface {

    @POST("signup.php")
    fun register(
        @Body body:RequestRegister
    ):Call<ResponseRegister>
//    ):NetworkResponse<ResponseRegister,Error>
    //return Call 일때 enqueue -> callback / onResponse, onFailure 쓰는거

    @POST("login.php")
    fun login(
        @Body body:RequestLogin
    ):Call<ResponseLogin>
}