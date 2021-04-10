package com.example.travelrecordapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.travelrecordapp.databinding.ActivityMainBinding
import com.example.travelrecordapp.ui.detail.MediaListActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var player: SimpleExoPlayer? = null
    //TODO 여기 오디오 바꾸기 
    private val songUrl: String = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        NavigationUI.setupWithNavController(binding.bottomNav,findNavController(R.id.navi_host))

        initMusicPlayer()
        binding.pcvMain.findViewById<ImageView>(R.id.btn_audio_finish).setOnClickListener {
            //TODO 플레이어 종료
            binding.pcvMain.hide() //지금은 hide만
        }

        binding.pcvMain.findViewById<ImageView>(R.id.btn_audio_list).setOnClickListener {
            //FIXME 넘어갈때 player 객체 같이 넘겨줘야할듯
            val intent = Intent(this@MainActivity,MediaListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initMusicPlayer(){
        if(player == null){
            player = SimpleExoPlayer.Builder(this).build()
            binding.pcvMain.player = player
            val defaultHttpDataSourceFactory =
                DefaultHttpDataSourceFactory(getString(R.string.app_name))
            val mediaSource = ProgressiveMediaSource.Factory(defaultHttpDataSourceFactory)
                .createMediaSource(Uri.parse(songUrl))
            player!!.prepare(mediaSource)
        }
        binding.pcvMain.showTimeoutMs=0

    }
}