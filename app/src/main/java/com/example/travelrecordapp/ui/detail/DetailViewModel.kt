package com.example.travelrecordapp.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.DetailTourData
import com.example.travelrecordapp.data.Event

class DetailViewModel(detailData: DetailTourData) :ViewModel() {

    val data = detailData
    val audioList = data.audioList
    val videoList = data.videoList
    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }

}