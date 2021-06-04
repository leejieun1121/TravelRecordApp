package com.example.travelrecordapp.di

import android.app.Application
import com.example.travelrecordapp.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


//ApplicationComponet -> SingletonComponent 로 이름변경
@InstallIn(SingletonComponent::class)
@Module
object AuthModule {

    @Singleton
    @Provides
    //바로 밑의 provideGoogleSingInClient 메소드에 googleSignInOptions를 제공
    //발급받은 clientID를 이용해서 GoogleSignInOptions객체 생성, 유저의 ID와 기본적인 프로필요청하는데 사용
    fun provideGoogleSignInOptions(application:Application )=
         GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(application.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

    @Singleton
    @Provides
    //생성된 GoogleSignInOptions를 사용해서 GoogleSignInClient객체를 만듦
    fun provideGoogleSignInClient(application:Application, googleSignInOptions:GoogleSignInOptions)=
         GoogleSignIn.getClient(application, googleSignInOptions)

}