package com.example.travelrecordapp.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.RequestTourList
import com.example.travelrecordapp.data.ResponseTourlist
import com.example.travelrecordapp.data.repository.TourRepository

class HomeViewModel @ViewModelInject constructor(
        private val tourRepository : TourRepository
): ViewModel() {

    fun getTourList(){
        tourRepository.getTourList(RequestTourList("1"),object:TourRepository.GetDataCallback<ResponseTourlist>{
            override fun onSuccess(data: ResponseTourlist?) {
                if(data!=null){
                    Log.d("tourlist",data.data.toString())

                }
            }

            override fun onFailure(throwable: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}