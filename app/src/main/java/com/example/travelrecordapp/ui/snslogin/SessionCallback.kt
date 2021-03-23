package com.example.travelrecordapp.ui.snslogin

import android.util.Log
import com.example.travelrecordapp.ui.login.LoginActivity
import com.kakao.auth.ISessionCallback
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException

class SessionCallback  : ISessionCallback {
    // 로그인에 성공한 상태
    override fun onSessionOpened() {
        requestMe()
    }

    // 로그인에 실패한 상태
    override fun onSessionOpenFailed(exception: KakaoException) {
        Log.e("tag", "onSessionOpenFailed : " + exception.message)
    }

    // 사용자 정보 요청
    private fun requestMe() {
        UserManagement.getInstance()
            .me(object : MeV2ResponseCallback() {
                override fun onSessionClosed(errorResult: ErrorResult) {
                    Log.e("tag", "세션이 닫혀 있음: $errorResult")
                }

                override fun onFailure(errorResult: ErrorResult) {
                    Log.e("tag", "사용자 정보 요청 실패: $errorResult")
                }

                override fun onSuccess(result: MeV2Response) {
                    Log.i("tag", "사용자 아이디: " + result.id)
                }
            })
    }
}