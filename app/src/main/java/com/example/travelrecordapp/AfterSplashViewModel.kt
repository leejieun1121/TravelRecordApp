package com.example.travelrecordapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class AfterSplashViewModel : ViewModel() {
    private val _loginActivityEvent = MutableLiveData<Event<Unit>>()
    val loginActivityEvent : LiveData<Event<Unit>>
    get() = _loginActivityEvent

    private val _registerActivityEvent = MutableLiveData<Event<Unit>>()
    val registerActivityEvent : LiveData<Event<Unit>>
        get() = _registerActivityEvent


    fun openLoginActivity(){
        _loginActivityEvent.value = Event(Unit)
    }

    fun openRegisterActivity(){
        _registerActivityEvent.value = Event(Unit)
    }
}