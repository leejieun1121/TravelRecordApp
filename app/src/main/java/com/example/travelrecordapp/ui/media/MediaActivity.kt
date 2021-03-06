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
import com.example.travelrecordapp.data.EventObserver
import com.example.travelrecordapp.data.VideoInfo
import com.example.travelrecordapp.databinding.ActivityMediaBinding
import com.example.travelrecordapp.util.MusicService
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector

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


        //audioListę°€ null -> video
        //videoListę°€ null -> audio

        viewModel.apply {
            finishEvent.observe(this@MediaActivity, EventObserver{
                finish()
            })
        }

        binding.viewPager.adapter = ViewPagerAdapter(viewModel.list.value!!, this)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(  //ěŠ¤í?¬ëˇ¤ ě??í?ś ëł€ę˛˝
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("indexCheck00", "??")

                prevnextMusicPlayer(position)

            }

            override fun onPageSelected(position: Int) { //í?´ë¦­ ë??ěť„ë•Ś
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })

        player!!.addListener(object : Player.EventListener {
            //ěž¬ě?ťěť´ ë‹¤ëĄ¸ ëŻ¸ë””ě–´ í•­ëŞ©ěśĽëˇś ě „í™? ë?  ë•Ś ę°?ě§€
            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                super.onMediaItemTransition(mediaItem, reason)
            }

            //
            override fun onPlayerStateChanged(playWhenReady: Boolean, playbackState: Int) {
                when (playbackState) {
                    Player.STATE_IDLE -> {
                    }
                    Player.STATE_BUFFERING -> {
                    }
                    Player.STATE_READY -> {
                    }
                    Player.STATE_ENDED -> {
                    }
                    else -> {
                    }
                }
            }
        })
    }

    private fun initMusicPlayer(){
        binding.pcvMedia.showTimeoutMs=0
        binding.pcvMedia2.showTimeoutMs=0
        if(player == null){
            val trackSelector = DefaultTrackSelector(this)
            trackSelector.setParameters(trackSelector.buildUponParameters().setMaxAudioBitrate(1000))
            player = SimpleExoPlayer.Builder(this).apply { setTrackSelector(trackSelector) }.build()
            binding.pcvMedia.player = player
            binding.pcvMedia2.player = player

            //í•´ë‹ą ě§€ě—­ěť? ě?¤ë””ě?¤íŚŚěťĽ ę°Żě??ë§Śí?Ľ í”Śë ?ěť´ë¦¬ěŠ¤íŠ¸ě—? ě¶”ę°€í•´ě¤Ś
            for(i in list.iterator()){
                player!!.addMediaItem(MediaItem.fromUri(Uri.parse(i.songUrl)))
            }

            player!!.prepare()
            player!!.seekTo(currentWindow, playbackPosition) //í”Śë ?ěť´ě–´ě—?ę˛Ś íŠąě • ě°˝ ë‚´ě—?ě„ś íŠąě •ěś„ěą?ëĄĽ ě°ľëŹ„ëˇť ě§€ě‹ś
            player!!.playWhenReady = playWhenReady //ěž¬ě?ťěť„ ěś„í•ś ëŞ¨ë“  ë¦¬ě†ŚěŠ¤ëĄĽ íšŤë“ťí•?ěž?ë§?ěž? ěž¬ě?ťěť„ ě‹śěž‘í• ě§€ ě—¬ë¶€ëĄĽ í”Śë ?ěť´ě–´ě—?ę˛Ś ě•Śë ¤ě¤Ś true -> ěž?ëŹ™ěž¬ě?ť
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

    private fun prevnextMusicPlayer(position: Int){
        initMusicPlayer()
        player!!.seekToDefaultPosition(position)
        player!!.prepare()
        Log.d("indexCheck", position.toString())

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
//        Log.d("musicěť¸ëŤ±ěŠ¤",position.toString())
    }

    private fun callService(){
        //ě„śëą„ěŠ¤ ě‹śěž‘
        val intent = Intent(this, MusicService::class.java)
        startService(intent)

        //ě„śëą„ěŠ¤ ě˘…ëŁŚ
//        val intent = Intent(this,MusicService::class.java)
//        stopService(intent)
    }

}

interface onClickViewPager{
    fun onClickViewPager(position: Int)
}