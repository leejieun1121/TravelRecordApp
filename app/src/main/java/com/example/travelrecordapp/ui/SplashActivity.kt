package com.example.travelrecordapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.travelrecordapp.MainActivity
import com.example.travelrecordapp.R

class SplashActivity : AppCompatActivity() {
    //핸들러를 이용한 splash는 지양하자
    // -> 그냥 2초를 버리는것과 마찬가지다 , 비효율적 !
//    internal var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

//        handler.postDelayed({ val intent = Intent(baseContext, MainActivity::class.java) startActivity(intent) finish() }, 2000)

    }
}