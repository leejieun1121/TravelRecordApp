package com.example.travelrecordapp.util

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MusicService : Service() { //백그라운드에서 실행을 위해 필요
    //Service객체와 화면단 사이에 통신할때 사용하는 메서드
    //데이터 전달 필요 없으면 return null
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    //서비스에서 가장 먼저 호출됨(최초 한번)
    override fun onCreate() {
        super.onCreate()
    }

    //서비스 호출될때마다 실행
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }

    //서비스 종료될때 실행
    override fun onDestroy() {
        super.onDestroy()
    }

}