package com.example.travelrecordapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event
import com.example.travelrecordapp.data.User
import com.example.travelrecordapp.data.repository.AuthRepository
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class AfterSplashViewModel() : ViewModel() {

    val authRepository = AuthRepository()
    private val _loginActivityEvent = MutableLiveData<Event<Unit>>()
    val loginActivityEvent : LiveData<Event<Unit>>
    get() = _loginActivityEvent

    private val _registerActivityEvent = MutableLiveData<Event<Unit>>()
    val registerActivityEvent : LiveData<Event<Unit>>
        get() = _registerActivityEvent

    var authenticatedUser: LiveData<FirebaseUser>? = null


    fun openLoginActivity(){
        _loginActivityEvent.value = Event(Unit)
    }

    fun openRegisterActivity(){
        _registerActivityEvent.value = Event(Unit)
    }

    fun signInWithGoogle(googleAuthCredential: AuthCredential){
        authenticatedUser = authRepository.firebaseSignInWithGoogle(googleAuthCredential)

    }
}