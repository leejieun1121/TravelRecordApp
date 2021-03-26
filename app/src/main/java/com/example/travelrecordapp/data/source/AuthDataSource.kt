package com.example.travelrecordapp.data.source

import com.example.travelrecordapp.data.RequestLogin
import com.example.travelrecordapp.data.ResponseLogin
import com.example.travelrecordapp.data.User
import com.example.travelrecordapp.data.repository.AuthRepository

interface AuthDataSource {
    fun requestLogin(user : RequestLogin,callback: AuthRepository.GetDataCallback<ResponseLogin>)

}