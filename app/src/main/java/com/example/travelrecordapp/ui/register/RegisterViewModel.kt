package com.example.travelrecordapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event

class RegisterViewModel : ViewModel() {
    private val _registerOkEvent = MutableLiveData<Event<Unit>>()
    val registerOkEvent : LiveData<Event<Unit>>
    get() = _registerOkEvent

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    fun registerOk(){
        _registerOkEvent.value = Event(Unit)
    }

    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }
}