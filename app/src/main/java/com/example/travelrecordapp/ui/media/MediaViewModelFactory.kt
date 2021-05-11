package com.example.travelrecordapp.ui.media

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelrecordapp.data.AudioInfo
import com.example.travelrecordapp.data.DetailTourData
import com.example.travelrecordapp.data.VideoInfo
import com.example.travelrecordapp.ui.detail.DetailViewModel
import java.lang.IllegalArgumentException

class MediaViewModelFactory (private val audioList: List<AudioInfo>?, private val videoList: List<VideoInfo>?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(MediaViewModel::class.java)){
            return MediaViewModel(audioList, videoList) as T

        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}