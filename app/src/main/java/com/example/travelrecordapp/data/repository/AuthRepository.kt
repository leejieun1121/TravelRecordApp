package com.example.travelrecordapp.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepository(
//        private val authRemoteDataSource : AuthDataSource,
//        private val authLocalDataSource : AuthDataSource
        )
{

    val auth = FirebaseAuth.getInstance()

    fun firebaseSignInWithGoogle(googleAuthCredential : AuthCredential) : MutableLiveData<FirebaseUser>{
        val user = MutableLiveData<FirebaseUser>()
        auth.signInWithCredential(googleAuthCredential)
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
}