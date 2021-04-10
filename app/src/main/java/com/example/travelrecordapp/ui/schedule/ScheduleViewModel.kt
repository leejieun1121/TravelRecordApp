package com.example.travelrecordapp.ui.schedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event

class ScheduleViewModel : ViewModel() {
    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    fun backToHomeFragment(){
        _finishEvent.value = Event(Unit)
    }
}