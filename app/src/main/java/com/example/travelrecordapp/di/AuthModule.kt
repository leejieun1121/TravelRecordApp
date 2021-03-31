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
    fun provideGoogleSignInOptions(application:Application )=
         GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(application.getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

    @Singleton
    @Provides
    fun provideGoogleSignInClient(application:Application, googleSignInOptions:GoogleSignInOptions)=
         GoogleSignIn.getClient(application, googleSignInOptions)

}