package com.example.travelrecordapp.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.Event
import com.example.travelrecordapp.data.RequestLogin
import com.example.travelrecordapp.data.RequestRegister
import com.example.travelrecordapp.data.ResponseRegister
import com.example.travelrecordapp.data.repository.AuthRepository
import com.example.travelrecordapp.data.source.local.AuthLocalDataSource
import com.example.travelrecordapp.data.source.remote.AuthRemoteDataSource

class RegisterViewModel : ViewModel() {

    private val authLocalDataSource = AuthLocalDataSource()
    private val authRemoteDataSource = AuthRemoteDataSource()

    private val authRepository = AuthRepository(authRemoteDataSource)

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    private val _emptyWarning = MutableLiveData<Event<Unit>>()
    val emptyWarning : LiveData<Event<Unit>>
        get() = _emptyWarning

    //TODO Register 성공했을때 데이터 -> 변수 만들어서 저장 !

    val nickname = MutableLiveData<String>()
    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordCheck = MutableLiveData<String>()

    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }

    fun requestRegister(){
        //빈곳 존재
        if(email.value.isNullOrEmpty()||password.value.isNullOrEmpty()||passwordCheck.value.isNullOrEmpty()||nickname.value.isNullOrEmpty()){
            _emptyWarning.value = Event(Unit)
        }else { //빈곳 없을때 ,
            //TODO 닉네임 중복이나 패스워드 불일치 문제일때 어떻게 값이 오는지
            //TODO provider, token값 뭘 넣어줘야하는지
                // TODO 어떤게 달라야 정상가입이 완료되는건지 모르겠음
            val user = RequestRegister(email.value.toString(), password.value.toString(), "provider", "token", nickname.value.toString())
            authRepository.requestRegister(user, object : AuthRepository.GetDataCallback<ResponseRegister> {
                override fun onSuccess(data: ResponseRegister?) {
                    if (data != null) {
                        Log.d("tagRegister", data.message)

                    } else {
                        //존재하지 않는 계정
                        Log.d("tagRegister", "존재 x ")
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    throwable.printStackTrace()
                }

            })
        }
    }
}