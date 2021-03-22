package com.example.travelrecordapp.data

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Converter
import java.lang.reflect.Type

class NetworkResponseAdapter <S : Any, E : Any>(
    private val successType : Type,
    private val errorbodyConverter : Converter<ResponseBody, E>
) : CallAdapter<S,Call<NetworkResponse<S,E>>>{

    override fun responseType() = successType

    override fun adapt(call: Call<S>): Call<NetworkResponse<S, E>> {
        return HandleNetworkResponse(call,errorbodyConverter)
    }
}