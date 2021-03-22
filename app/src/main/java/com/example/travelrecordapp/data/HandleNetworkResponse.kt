package com.example.travelrecordapp.data

import okhttp3.Request
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class HandleNetworkResponse<S : Any, E : Any>(
    private val delegate : Call<S>,
    private val errorConverter : Converter<ResponseBody, E>
) : Call<NetworkResponse<S, E>>{
    override fun enqueue(callback: Callback<NetworkResponse<S, E>>) {
        return delegate.enqueue(object : Callback<S>{
            override fun onResponse(call: Call<S>, response: Response<S>) {

                val body = response.body()
                response.body()
                val code = response.code()
                val error = response.errorBody()

                if (response.isSuccessful){
                    body?.let {
                        callback.onResponse(
                            this@HandleNetworkResponse,
                            Response.success(NetworkResponse.Success(it)))
                    } ?: let {
                        callback.onResponse(
                            this@HandleNetworkResponse,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }
                } else {
                    val errorBody = when{
                        error == null -> null
                        error.contentLength() == 0L -> null
                        else -> try {
                            errorConverter.convert(error)
                        } catch (ex : Exception){
                            null
                        }
                    }

                    errorBody?.let {
                        callback.onResponse(
                            this@HandleNetworkResponse,
                            Response.success(NetworkResponse.ApiError(errorBody,code))
                        )
                    } ?: let {
                        callback.onResponse(
                            this@HandleNetworkResponse,
                            Response.success(NetworkResponse.UnknownError(null))
                        )
                    }

                }
            }

            override fun onFailure(call: Call<S>, t: Throwable) {
                val response = when(t){
                    is IOException ->  NetworkResponse.NetworkError(t)
                    else -> NetworkResponse.UnknownError(t)
                }
                callback.onResponse(this@HandleNetworkResponse, Response.success(response))

            }
        })

    }

    override fun clone(): Call<NetworkResponse<S, E>> {
        return HandleNetworkResponse(delegate.clone(), errorConverter)
    }

    override fun execute(): Response<NetworkResponse<S, E>> {
        throw UnsupportedOperationException("This Handler doesn't support execute")
    }

    override fun isExecuted(): Boolean {
        return delegate.isExecuted
    }

    override fun cancel() {
        delegate.cancel()
    }

    override fun isCanceled(): Boolean {
        return delegate.isCanceled
    }

    override fun request(): Request {
        return delegate.request()
    }
}