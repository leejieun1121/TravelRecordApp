package com.example.travelrecordapp.ui.home

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.RequestTourList
import com.example.travelrecordapp.data.ResponseTourlist
import com.example.travelrecordapp.data.TourData
import com.example.travelrecordapp.data.repository.TourRepository

class HomeViewModel @ViewModelInject constructor(
        private val tourRepository : TourRepository
): ViewModel() {

    private val _position = MutableLiveData<Int>()
    val position : LiveData<Int>
        get() = _position

    private val _tourList = MutableLiveData<List<TourData>>()
    val tourList : LiveData<List<TourData>>
    get() = _tourList

    fun getTourList(){
        tourRepository.getTourList(RequestTourList("1"),object:TourRepository.GetDataCallback<ResponseTourlist>{
            override fun onSuccess(data: ResponseTourlist?) {
                if(data!=null){
                    _tourList.postValue(data.data.tourlist)
                }
            }

            override fun onFailure(throwable: Throwable) {
                throwable.printStackTrace()
            }

        })
    }

    fun getPosition(position:Int){
        _position.value = position
    }
}