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
import com.example.travelrecordapp.ui.media.MediaActivity
import com.example.travelrecordapp.ui.media.MediaListActivity
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var player: SimpleExoPlayer? = null
    //TODO 여기 오디오 바꾸기 
    private val songUrl: String = ""

    private var playbackPosition = 0L
    private var currentWindow = 0
    private var playWhenReady = true

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        NavigationUI.setupWithNavController(binding.bottomNav,findNavController(R.id.navi_host))

//        initMusicPlayer()
        binding.pcvMain.findViewById<ImageView>(R.id.btn_audio_finish).setOnClickListener {
            //TODO 플레이어 종료
            binding.pcvMain.hide() //지금은 hide만
        }

        binding.pcvMain.findViewById<ImageView>(R.id.btn_audio_list).setOnClickListener {
            //FIXME 넘어갈때 player 객체 같이 넘겨줘야할듯
            val intent = Intent(this@MainActivity, MediaListActivity::class.java)
            startActivity(intent)
        }
        binding.pcvMain.setOnClickListener {
            val intent = Intent(this@MainActivity, MediaActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initMusicPlayer(){
        binding.pcvMain.showTimeoutMs=0

        if(player == null){
            player = SimpleExoPlayer.Builder(this).build()
            binding.pcvMain.player = player
            val defaultHttpDataSourceFactory =
                DefaultHttpDataSourceFactory(getString(R.string.app_name))
            val mediaSource = ProgressiveMediaSource.Factory(defaultHttpDataSourceFactory)
                .createMediaSource(Uri.parse(songUrl))
            player!!.prepare(mediaSource)
            player!!.seekTo(currentWindow, playbackPosition)
            player!!.playWhenReady = playWhenReady
        }
    }

    private fun releaseMusicPlayer() {
        player?.let {
            playbackPosition = it.currentPosition
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
            player = null
        }
    }

//    override fun onResume() {
//        super.onResume()
//        initMusicPlayer()
//    }
//
//    override fun onRestart() {
//        super.onRestart()
//        initMusicPlayer()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        releaseMusicPlayer()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        releaseMusicPlayer()
//    }
}