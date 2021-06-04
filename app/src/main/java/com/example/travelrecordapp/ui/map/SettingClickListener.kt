package com.example.travelrecordapp.ui.map

import android.content.Intent
import android.provider.Settings
import android.view.View

class SettingClickListener : View.OnClickListener {
    override fun onClick(view: View?) {
        val intent = Intent(Settings.ACTION_SETTINGS)
        view?.context?.startActivity(intent)
        //TODO 설정화면에서 권한 체크했다면 바로 위치 이동하도록 ㅠ
    }


}