package com.example.travelrecordapp.ui.media

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.AudioInfo
import com.example.travelrecordapp.data.Event
import com.example.travelrecordapp.data.MediaData
import com.example.travelrecordapp.data.VideoInfo

class MediaViewModel(audioList: List<AudioInfo>?,videoList:List<VideoInfo>?) : ViewModel() {

    val audioList = audioList
    val videoList = videoList

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
    get() = _finishEvent


    private val _position = MutableLiveData<Int>()
    val position : LiveData<Int>
        get() = _position

    private val _list = MutableLiveData<List<Any>>()
    val list : LiveData<List<Any>>
        get() = _list

    init {
        if(audioList.isNullOrEmpty()){
            _list.value = this.videoList
        }else {
            _list.value = this.audioList
        }
    }

    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }

}