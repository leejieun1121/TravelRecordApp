package com.example.travelrecordapp.data

import java.io.Serializable

data class DetailTourData (
        val tourName : String,
        val tourImage: String,
        val tourDate : String,
        val audioList : List<AudioInfo>,
        val videoList : List<VideoInfo>,
        val lat :Double,
        val lng : Double,
        val phoneNum:String,
        val explain:String,
        val detailExplain:String
        ):Serializable

data class AudioInfo(
    val title:String,
    val audioExplain:String,
    val image:String,
    val songUrl:String
):Serializable

data class VideoInfo(
    val title:String,
    val videoExplain:String,
    val image:String,
    val videoUrl:String
):Serializable