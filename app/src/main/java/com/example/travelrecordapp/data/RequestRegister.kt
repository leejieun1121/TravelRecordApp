package com.example.travelrecordapp.data

data class RequestRegister (
    val email:String,
    val password:String,
    val provider:String,
    val token:String,
    val nickname:String
)

