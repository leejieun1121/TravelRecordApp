package com.example.travelrecordapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event
import com.example.travelrecordapp.data.RequestLogin
import com.example.travelrecordapp.data.ResponseLogin
import com.example.travelrecordapp.data.User
import com.example.travelrecordapp.data.repository.AuthRepository
import com.example.travelrecordapp.data.source.local.AuthLocalDataSource
import com.example.travelrecordapp.data.source.remote.AuthRemoteDataSource

class LoginViewModel : ViewModel() {

    private val authLocalDataSource = AuthLocalDataSource()
    private val authRemoteDataSource = AuthRemoteDataSource()

    private val authRepository = AuthRepository(authRemoteDataSource)

    private val _findPwActivityEvent = MutableLiveData<Event<Unit>>()
    val findPwActivityEvent : LiveData<Event<Unit>>
        get() = _findPwActivityEvent

    private val _loginOkEvent = MutableLiveData<Event<Unit>>()
    val loginOkEvent : LiveData<Event<Unit>>
    get() = _loginOkEvent

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    private val _userData = MutableLiveData<User>()
    val userData : LiveData<User>
        get() = _userData

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun openFindPwActivity(){
        _findPwActivityEvent.value = Event(Unit)

    }

    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }

    fun requestLogin(){
        val user = RequestLogin(email.value.toString(),password.value.toString())
        authRepository.requestLogin(user,object:AuthRepository.GetDataCallback<ResponseLogin>{
            override fun onSuccess(data: ResponseLogin?) {
                if(data!=null){
                    //존재하는 계정
                    _userData.postValue(data.data)
                }else{
                    //존재하지 않는 계정
                    _userData.postValue(null)
                }
            }

            //인터넷 연결 x
            override fun onFailure(throwable: Throwable) {
                throwable.printStackTrace()
            }

        })
    }
}