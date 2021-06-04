package com.example.travelrecordapp.ui.login

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
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
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository : AuthRepository
)  : ViewModel() {

    private val _findPwActivityEvent = MutableLiveData<Event<Unit>>()
    val findPwActivityEvent : LiveData<Event<Unit>>
        get() = _findPwActivityEvent

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    private val _userData = MutableLiveData<User>()
    val userData : LiveData<User>
        get() = _userData

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage : LiveData<String>
        get() = _toastMessage

    fun openFindPwActivity(){
        _findPwActivityEvent.value = Event(Unit)

    }

    fun finishActivity(){
        _finishEvent.value = Event(Unit)
    }


    fun requestLogin(){
        if(email.value.isNullOrEmpty() ||password.value.isNullOrEmpty()){
            _toastMessage.value = "입력되지 않은 곳이 있습니다."
        }else{
            val user = RequestLogin(email.value.toString(),password.value.toString())
            authRepository.requestLogin(user,object:AuthRepository.GetDataCallback<ResponseLogin>{
                override fun onSuccess(data: ResponseLogin?) {
                    if(data!=null){
                        //존재하는 계정
                        _userData.postValue(data.data)
                        _toastMessage.value = "로그인 완료!"
                    }else{
                        //존재하지 않는 계정
                        _userData.postValue(null)
                        _toastMessage.value = "존재하지 않는 계정입니다."
                    }
                }

                //인터넷 연결 x
                override fun onFailure(throwable: Throwable) {
                    throwable.printStackTrace()
                }

            })
        }

    }
}