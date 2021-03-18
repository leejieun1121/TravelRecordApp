package com.example.travelrecordapp.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event

class RegisterViewModel : ViewModel() {
    private val _registerOkEvent = MutableLiveData<Event<Unit>>()
    val registerOkEvent : LiveData<Event<Unit>>
    get() = _registerOkEvent

    fun registerOk(){
        _registerOkEvent.value = Event(Unit)
    }
}