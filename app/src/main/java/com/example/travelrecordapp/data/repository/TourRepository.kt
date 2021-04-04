package com.example.travelrecordapp.data.repository

import com.example.travelrecordapp.data.RequestTourList
import com.example.travelrecordapp.data.ResponseTourlist
import com.example.travelrecordapp.data.source.TourDataSource

class TourRepository (
        private val tourRemoteDataSource : TourDataSource,
//        private val tourLocalDataSource : TourDataSource
        ){

        fun getTourList(useridx: RequestTourList, callback: GetDataCallback<ResponseTourlist>) {
                tourRemoteDataSource.getTourList(useridx,callback)
        }

        interface GetDataCallback<T>{
                fun onSuccess(data:T?)
                fun onFailure(throwable: Throwable)
        }

}