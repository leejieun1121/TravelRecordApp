package com.example.travelrecordapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event

class LoginViewModel : ViewModel() {

    private val _findPwActivityEvent = MutableLiveData<Event<Unit>>()
    val findPwActivityEvent : LiveData<Event<Unit>>
        get() = _findPwActivityEvent

    private val _loginOkEvent = MutableLiveData<Event<Unit>>()
    val loginOkEvent : LiveData<Event<Unit>>
    get() = _loginOkEvent

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    fun openMainActivity(){
        _loginOkEvent.value = Event(Unit)
    }

    fun openFindPwActivity(){
        _findPwActivityEvent.value = Event(Unit)

    }

    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }
}