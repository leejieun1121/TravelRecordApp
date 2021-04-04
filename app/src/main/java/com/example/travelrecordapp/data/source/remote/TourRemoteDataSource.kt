package com.example.travelrecordapp.data.source.remote

import com.example.travelrecordapp.data.RequestInterface
import com.example.travelrecordapp.data.RequestTourList
import com.example.travelrecordapp.data.ResponseTourlist
import com.example.travelrecordapp.data.repository.TourRepository
import com.example.travelrecordapp.data.source.TourDataSource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import javax.inject.Inject

class TourRemoteDataSource @Inject constructor(
        private val retrofit: RequestInterface
): TourDataSource {

    override fun getTourList(useridx: RequestTourList, callback: TourRepository.GetDataCallback<ResponseTourlist>) {
        retrofit.getTourList(useridx).enqueue(object: Callback<ResponseTourlist>{
            override fun onResponse(call: Call<ResponseTourlist>, response: Response<ResponseTourlist>) {
                callback.onSuccess(response.body())
            }

            override fun onFailure(call: Call<ResponseTourlist>, t: Throwable) {
                callback.onFailure(t)
            }

        })
    }

}