package com.example.travelrecordapp.ui.snslogin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event
import com.example.travelrecordapp.data.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser


class AfterSplashViewModel() : ViewModel() {

    private val authRepository = AuthRepository()
    private val _loginActivityEvent = MutableLiveData<Event<Unit>>()
    val loginActivityEvent : LiveData<Event<Unit>>
    get() = _loginActivityEvent

    private val _registerActivityEvent = MutableLiveData<Event<Unit>>()
    val registerActivityEvent : LiveData<Event<Unit>>
        get() = _registerActivityEvent

    //Facebook, Google 로그인 방법마다 user변수를 다르게 둬야할까?
    var authenticatedUser: LiveData<FirebaseUser>? = null

    fun openLoginActivity(){
        _loginActivityEvent.value = Event(Unit)
    }

    fun openRegisterActivity(){
        _registerActivityEvent.value = Event(Unit)
    }

    fun signInWithFirebase(credential: AuthCredential){
        authenticatedUser = authRepository.signInWithFirebase(credential)
    }


}