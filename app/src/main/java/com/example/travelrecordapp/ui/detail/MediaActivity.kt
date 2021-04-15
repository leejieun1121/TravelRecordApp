package com.example.travelrecordapp.ui.detail

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.example.travelrecordapp.BR
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.MediaData
import com.example.travelrecordapp.data.TourData
import com.example.travelrecordapp.databinding.ActivityMediaBinding
import com.example.travelrecordapp.databinding.ItemMyPlaceBinding
import com.example.travelrecordapp.ui.home.HomeViewModel
import com.example.travelrecordapp.util.BaseRecyclerAdapter
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory

class MediaActivity : AppCompatActivity(),onClickViewPager {
    private lateinit var binding: ActivityMediaBinding
    private val viewModel: MediaViewModel by viewModels()

    private var player: SimpleExoPlayer? = null
    private var songUrl: String = ""
    lateinit var list : List<MediaData>
    private var playbackPosition = 0L
    private var currentWindow = 0
    private var playWhenReady = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_media)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.apply {
            finishEvent.observe(this@MediaActivity, {
              finish()
            })
        }

        list = listOf(MediaData("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3","고씨동굴1","고씨동굴111")
            ,MediaData("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3","고씨동굴2","고씨동굴222")
            ,MediaData("https://www.soundhelix.com/examples/mp3/SoundHelix-Song-3.mp3","고씨동굴3","고씨동굴333")
        )
        binding.viewPager.adapter = ViewPagerAdapter(list,this)

        binding.viewPager.addOnPageChangeListener(object:ViewPager.OnPageChangeListener{
            override fun onPageScrolled(  //스크롤 상태 변경
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
            override fun onPageSelected(position: Int) { //클릭 됐을때
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })


    }

    private fun initMusicPlayer(){
        binding.pcvMedia.showTimeoutMs=0

        if(player == null){
            player = SimpleExoPlayer.Builder(this).build()
            binding.pcvMedia.player = player
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

    private fun resetMusicPlayer() {
        player?.let {
            playbackPosition = 0
            currentWindow = it.currentWindowIndex
            playWhenReady = it.playWhenReady
            it.release()
            player = null
        }
    }

    override fun onResume() {
        super.onResume()
        initMusicPlayer()
    }

    override fun onRestart() {
        super.onRestart()
        initMusicPlayer()
    }

    override fun onPause() {
        super.onPause()
        releaseMusicPlayer()
    }

    override fun onStop() {
        super.onStop()
        releaseMusicPlayer()
    }

    override fun onClickViewPager(position: Int) {
        resetMusicPlayer()
        songUrl = list[position].url
        initMusicPlayer()
    }
}

interface onClickViewPager{
    fun onClickViewPager(position:Int)
}