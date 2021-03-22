package com.example.travelrecordapp.data

data class ResponseLogin (
    val success : Boolean,
    val data : User,
    val message : String
    )

data class User(
    val token:String,
    val email:String,
    val userIdx:String,
    val nickname:String
)