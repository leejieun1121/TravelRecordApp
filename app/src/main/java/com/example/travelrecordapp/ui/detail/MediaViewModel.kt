package com.example.travelrecordapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event
import com.example.travelrecordapp.data.MediaData

class MediaViewModel : ViewModel() {

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
    get() = _finishEvent

    private val _mediaDataList= MutableLiveData<List<MediaData>>()
    val mediaDataList : LiveData<List<MediaData>>
    get() = _mediaDataList

    private val _position = MutableLiveData<Int>()
    val position : LiveData<Int>
        get() = _position


    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }

    fun getPosition(position:Int){
        _position.value = position
    }
}