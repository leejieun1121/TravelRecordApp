package com.example.travelrecordapp.ui.snslogin

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.MainActivity
import com.example.travelrecordapp.data.Event
import com.example.travelrecordapp.data.repository.AuthRepository
import com.example.travelrecordapp.data.source.local.AuthLocalDataSource
import com.example.travelrecordapp.data.source.remote.AuthRemoteDataSource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.LogoutResponseCallback
import com.kakao.util.exception.KakaoException
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AfterSplashViewModel @Inject constructor(
    private val authRepository : AuthRepository
) : ViewModel() {
//    private val AuthLocalDataSource = AuthLocalDataSource()
//    private val AuthRemoteDataSource = AuthRemoteDataSource()
//
//    private val authRepository = AuthRepository(AuthRemoteDataSource)

    private val _loginActivityEvent = MutableLiveData<Event<Unit>>()
    val loginActivityEvent : LiveData<Event<Unit>>
    get() = _loginActivityEvent

    private val _registerActivityEvent = MutableLiveData<Event<Unit>>()
    val registerActivityEvent : LiveData<Event<Unit>>
        get() = _registerActivityEvent

    private val _kakaoSession= MutableLiveData<Session>()
    val kakaoSession : LiveData<Session>
        get() = _kakaoSession

    private val _kakaoLogoutEvent = MutableLiveData<Event<Unit>>()
    val kakaoLogoutEvent : LiveData<Event<Unit>>
        get() = _kakaoLogoutEvent

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

    fun signInWithKakao(){
        _kakaoSession.postValue(authRepository.getKakaoSession())
    }

    fun signOutWithKakao(){
        UserManagement.getInstance().requestLogout(object : LogoutResponseCallback() {
            override fun onCompleteLogout() {
                _kakaoLogoutEvent.postValue(Event(Unit))
            }
        })
    }

}