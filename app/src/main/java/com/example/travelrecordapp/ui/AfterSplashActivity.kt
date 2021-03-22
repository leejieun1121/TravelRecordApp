package com.example.travelrecordapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.travelrecordapp.MainActivity
import com.example.travelrecordapp.R
import com.example.travelrecordapp.databinding.ActivityAfterSplashBinding
import com.example.travelrecordapp.ui.login.LoginActivity
import com.example.travelrecordapp.ui.register.RegisterActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase

class AfterSplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAfterSplashBinding
    private val viewModel: AfterSplashViewModel by viewModels()

    private lateinit var callbackManager: CallbackManager
    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //스플래시테마 -> 다시 원래대로
        setTheme(R.style.Theme_TravelRecordApp)

        setupBinding()

        // FIXME 여기 DI로 바꿔보자
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(this, gso)

        doFacebookLogin()

        viewModel.apply {
            //로그인 버튼
            loginActivityEvent.observe(this@AfterSplashActivity){
                val intent = Intent(this@AfterSplashActivity, LoginActivity::class.java)
                startActivity(intent)
            }
            //회원가입 버튼
            registerActivityEvent.observe(this@AfterSplashActivity){
                val intent = Intent(this@AfterSplashActivity, RegisterActivity::class.java)
                startActivity(intent)
            }
            //여기서 authenticatedUser 값이 변할때는 왜 관찰 못하고 함수 안에서만 반응할까
        }

        //구글 로그인 버튼 클릭
        binding.btnGoogleLogin.setOnClickListener {
            val signInIntent = googleSignInClient.signInIntent
            startActivityForResult(signInIntent, RC_SIGN_IN)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //페이스북 로그인 콜백 설정
        callbackManager.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                    //구글 계정이 있다면 그 계정의 토큰으로 로그인 시도
                val account = task.getResult(ApiException::class.java)!!
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("tag", "Google sign in failed", e)
                // ...
            }
        }
    }

    //데이터바인딩, 라이프사이클, 뷰모델 셋팅
    private fun setupBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_after_splash)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }

    //FIXME 구글, 페이스북 로그인 인텐트 넘어가는 코드 합칠 수 없을까?

    //구글계정 확인되면 로그인 시도
    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)

        viewModel.signInWithFirebase(credential)
        viewModel.authenticatedUser?.observe(this@AfterSplashActivity){
            if(it != null){
                //null아니라면 파이어베이스 로그인 된것
                val intent = Intent(this@AfterSplashActivity,MainActivity::class.java)
                startActivity(intent)
                //TODO 유저정보 넘기기, 지금은 FirebaseUser 로 되어있는데 이걸 어떻게 가공할까 ?
            }else{
                Toast.makeText(this@AfterSplashActivity,"로그인이 불가능합니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //페이스북 계정 확인되면 로그인 시도
    private fun doFacebookLogin(){
        // Initialize Facebook Login button
        callbackManager = CallbackManager.Factory.create()

        binding.btnFacebookLogin.setPermissions("email", "public_profile")
        binding.btnFacebookLogin.registerCallback(callbackManager, object :
                FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d("tag", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d("tag", "facebook:onError", error)
                // ...
            }
        })
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        //로그아웃 할때까지 자동로그인 되어있음
        val credential = FacebookAuthProvider.getCredential(token.token)
        viewModel.signInWithFirebase(credential)
        viewModel.authenticatedUser?.observe(this@AfterSplashActivity){
            if(it != null){
                val intent = Intent(this@AfterSplashActivity,MainActivity::class.java)
                startActivity(intent)
                //TODO 유저정보 넘기기, 지금은 FirebaseUser 로 되어있는데 이걸 어떻게 가공할까 ?
            }else{
                Toast.makeText(this@AfterSplashActivity,"로그인이 불가능합니다.",Toast.LENGTH_SHORT).show()
            }
        }
    }




}