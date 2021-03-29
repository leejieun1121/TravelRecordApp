package com.example.travelrecordapp.data.source

import com.example.travelrecordapp.data.*
import com.example.travelrecordapp.data.repository.AuthRepository

interface AuthDataSource {
    fun requestLogin(user : RequestLogin,callback: AuthRepository.GetDataCallback<ResponseLogin>)
    fun requestRegister(user : RequestRegister, callback: AuthRepository.GetDataCallback<ResponseRegister>)
}