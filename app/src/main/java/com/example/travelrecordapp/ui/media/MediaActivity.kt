package com.example.travelrecordapp.ui.media

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.AudioInfo
import com.example.travelrecordapp.data.VideoInfo
import com.example.travelrecordapp.databinding.ActivityMediaBinding
import com.example.travelrecordapp.util.MusicService
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class MediaActivity : AppCompatActivity(), onClickViewPager {
    private lateinit var binding: ActivityMediaBinding
    private val viewModel: MediaViewModel by viewModels()
    private lateinit var viewModelFactory : MediaViewModelFactory

    private var player: SimpleExoPlayer? = null
    private var songUrl: String = ""
    lateinit var list : List<AudioInfo>
    private var playbackPosition = 0L
    private var currentWindow = 0
    private var playWhenReady = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_media)
        binding.lifecycleOwner = this
        viewModelFactory= MediaViewModelFactory(
            intent.getSerializableExtra("audioList") as List<AudioInfo>?,
            intent.getSerializableExtra("videoList") as List<VideoInfo>?
        )
        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(MediaViewModel::class.java)

        list = viewModel.list.value as List<AudioInfo>


        //audioList가 null -> video
        //videoList가 null -> audio

        viewModel.apply {
            finishEvent.observe(this@MediaActivity, {
                finish()
            })
        }

        binding.viewPager.adapter = ViewPagerAdapter(viewModel.list.value!!, this)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(  //스크롤 상태 변경
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("indexCheck00","??")

                prevnextMusicPlayer(position)

            }

            override fun onPageSelected(position: Int) { //클릭 됐을때
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    private fun initMusicPlayer(){
        binding.pcvMedia.showTimeoutMs=0
        binding.pcvMedia2.showTimeoutMs=0
        if(player == null){
            player = SimpleExoPlayer.Builder(this).build()
            binding.pcvMedia.player = player
            binding.pcvMedia2.player = player

            //해당 지역의 오디오파일 갯수만큼 플레이리스트에 추가해줌
            for(i in list.iterator()){
                player!!.addMediaItem(MediaItem.fromUri(Uri.parse(i.songUrl)))
            }

            player!!.prepare()
            player!!.seekTo(currentWindow, playbackPosition) //플레이어에게 특정 창 내에서 특정위치를 찾도록 지시
            player!!.playWhenReady = playWhenReady //재생을 위한 모든 리소스를 획득하자마자 재생을 시작할지 여부를 플레이어에게 알려줌 true -> 자동재생
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
            currentWindow = 0
            playWhenReady = it.playWhenReady
            it.release()
            player = null
        }
    }

    private fun prevnextMusicPlayer(position:Int){
        initMusicPlayer()
        player!!.seekToDefaultPosition(position)
        player!!.prepare()
        Log.d("indexCheck",position.toString())

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
//        songUrl = list[position].songUrl
        prevnextMusicPlayer(position)
//        Log.d("music인덱스",position.toString())
    }

    private fun callService(){
        //서비스 시작
        val intent = Intent(this, MusicService::class.java)
        startService(intent)

        //서비스 종료
//        val intent = Intent(this,MusicService::class.java)
//        stopService(intent)
    }

}

interface onClickViewPager{
    fun onClickViewPager(position: Int)
}