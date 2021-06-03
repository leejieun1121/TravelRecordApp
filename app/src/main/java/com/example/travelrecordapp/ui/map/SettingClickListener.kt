package com.example.travelrecordapp.ui.map

import android.content.Intent
import android.provider.Settings
import android.view.View

class SettingClickListener : View.OnClickListener {
    override fun onClick(view: View?) {
        val intent = Intent(Settings.ACTION_SETTINGS)
        view?.context?.startActivity(intent)
    }


}