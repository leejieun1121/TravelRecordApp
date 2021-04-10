package com.example.travelrecordapp.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event

class MediaViewModel : ViewModel() {

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
    get() = _finishEvent

    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }}