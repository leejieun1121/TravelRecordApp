package com.example.travelrecordapp

import android.media.Image
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.travelrecordapp.databinding.ActivityAfterSplashBinding
import com.example.travelrecordapp.databinding.ActivityMainBinding
import com.example.travelrecordapp.ui.detail.DetailFragment
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var player: SimpleExoPlayer? = null
    private val songUrl: String = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        NavigationUI.setupWithNavController(binding.bottomNav,findNavController(R.id.navi_host))

        initMusicPlayer()
        binding.pcvMain.findViewById<ImageView>(R.id.btn_audio_finish).setOnClickListener {
            //TODO x버튼 처리
        }

        binding.pcvMain.findViewById<ImageView>(R.id.btn_audio_list).setOnClickListener {
            //TODO audio list fragment로 이동
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