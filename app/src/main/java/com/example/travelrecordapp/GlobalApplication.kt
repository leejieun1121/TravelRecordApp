package com.example.travelrecordapp

import android.app.Application
import com.example.travelrecordapp.ui.snslogin.KakaoSDKAdapter
import com.kakao.auth.KakaoSDK
import dagger.hilt.android.HiltAndroidApp

//TODO DI 하기
@HiltAndroidApp
class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        instance = this
        KakaoSDK.init(KakaoSDKAdapter())
    }

    override fun onTerminate() {
        super.onTerminate()
        instance = null
    }

    fun getGlobalApplicationContext(): GlobalApplication {
        checkNotNull(instance) { "this application does not inherit com.kakao.GlobalApplication" }
        return instance!!
    }

    companion object {
        var instance: GlobalApplication? = null
    }
}