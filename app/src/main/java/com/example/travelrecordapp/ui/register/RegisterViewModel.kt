package com.example.travelrecordapp.ui.register

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
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

class RegisterViewModel @ViewModelInject constructor(
    private val authRepository : AuthRepository
)   : ViewModel() {

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String>
        get() = _toastMessage

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
            _toastMessage.value = "입력하지 않은 곳이 있습니다."
        }else { //빈곳 없을때
            //TODO 닉네임 중복이나 패스워드 불일치 문제처리
                // TODO 성공일때 data=null이 맞는건가??
            val user = RequestRegister(email.value.toString(), password.value.toString(), "provider", "token", nickname.value.toString())
            authRepository.requestRegister(user, object : AuthRepository.GetDataCallback<ResponseRegister> {
                override fun onSuccess(data: ResponseRegister?) {
                    if(!data!!.success){
                        //네트워크 응답은 왔는데 회원가입이 불가능한 경우(이미 가입된 유저)
                        _toastMessage.value = "이미 가입된 유저입니다."
                    }else{
                        //정상 회원가입
                        _finishEvent.postValue(Event(Unit))
                        _toastMessage.value = "회원가입 완료!"
                    }
                }

                override fun onFailure(throwable: Throwable) {
                    throwable.printStackTrace()
                }

            })
        }
    }
}