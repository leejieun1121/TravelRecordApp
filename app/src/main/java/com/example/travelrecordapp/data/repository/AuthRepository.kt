package com.example.travelrecordapp.data.repository

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.travelrecordapp.data.RequestLogin
import com.example.travelrecordapp.data.ResponseLogin
import com.example.travelrecordapp.data.source.AuthDataSource
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.kakao.auth.Session

class AuthRepository(
        private val authRemoteDataSource : AuthDataSource,
//        private val authLocalDataSource : AuthDataSource
        )
{

    private val auth = FirebaseAuth.getInstance()
    private val user = MutableLiveData<FirebaseUser>()

    fun signInWithFirebase(credential : AuthCredential) : MutableLiveData<FirebaseUser>{
        auth.signInWithCredential(credential)
                .addOnCompleteListener {
                    task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("tag", "signInWithCredential:success")
                        user.value = auth.currentUser

                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("tag", "signInWithCredential:failure", task.exception)
                        user.value = null
                        // ...
                    }
                    // ...
                }
        return user
    }

    fun getKakaoSession():Session{
        return Session.getCurrentSession()
    }

    fun requestLogin(user: RequestLogin,callback: GetDataCallback<ResponseLogin>){
        authRemoteDataSource.requestLogin(user,callback)
    }

    interface GetDataCallback<T>{
        fun onSuccess(data:T?)
        fun onFailure(throwable: Throwable)
    }

}