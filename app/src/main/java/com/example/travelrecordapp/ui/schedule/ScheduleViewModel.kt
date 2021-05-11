package com.example.travelrecordapp.ui.schedule

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.travelrecordapp.data.AudioInfo
import com.example.travelrecordapp.data.DetailTourData
import com.example.travelrecordapp.data.Event
import com.example.travelrecordapp.data.VideoInfo

class ScheduleViewModel : ViewModel() {

    private val _position = MutableLiveData<Int>()
    val position : LiveData<Int>
        get() = _position

    private val _finishEvent = MutableLiveData<Event<Unit>>()
    val finishEvent : LiveData<Event<Unit>>
        get() = _finishEvent

    private val _scheduleList = MutableLiveData<List<DetailTourData>>()
    val scheduleList : LiveData<List<DetailTourData>>
    get() = _scheduleList

    fun backToHomeFragment(){
        _finishEvent.value = Event(Unit)
    }

    /*
    *  data class DetailData (
            val tourName : String,
            val tourDate : String,
            val audioList : List<AudioInfo>,
            val lat : Double,
            val lng : Double
            val phoneNum:String,
            val explain:String,
            val detailExplain:String
        )

        data class AudioInfo(
            val title:String,
            val audioExplain:String,
            val image:String,
            val songUrl:String
        )
        * */

    fun getScheduleData(){
        val audioList = listOf(AudioInfo("고씨동굴1","꽤 멋진 곳","","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"),
            AudioInfo("고씨동굴2","아주 예쁜 곳","","https://www.soundhelix.com/examples/mp3/SoundHelix-Song-2.mp3"))
        val videoList = listOf(VideoInfo("","","",""))
        val scheduleList = mutableListOf(
            DetailTourData("김삿갓 유적지1","","7/9 - PM 14:00",audioList,videoList,37.0794845,128.601784,"010-1234-5678","강원도 영월군 난고 김병연의 유적지","강원도 영월군 하동면 와석리 노루목에 조성된 " +
                    "강원도 영월군 하동면 와석리 노루목에 조성된 난고 김병연(金炳淵)의 유적지. 별호인 김삿갓으로 불리는 난고 김병연(1807~1863)을 기념하는 유적지와 부대시설이 조성되어 있다. 김삿갓 연구자료를 전시하고 있는 난고문학관과 많은 돌탑이 조성되어 있는 묘, 작은 성황당, 마대산을 따라 김삿갓이 살던 집터 등이 있다. ")
            ,DetailTourData("김삿갓 유적지2","","7/9 - PM 14:00",audioList,videoList,37.0794845,128.601784,"010-1234-5678","강원도 영월군 난고 김병연의 유적지","강원도 영월군 하동면 와석리 노루목에 조성된 " +
                    "강원도 영월군 하동면 와석리 노루목에 조성된 난고 김병연(金炳淵)의 유적지. 별호인 김삿갓으로 불리는 난고 김병연(1807~1863)을 기념하는 유적지와 부대시설이 조성되어 있다. 김삿갓 연구자료를 전시하고 있는 난고문학관과 많은 돌탑이 조성되어 있는 묘, 작은 성황당, 마대산을 따라 김삿갓이 살던 집터 등이 있다. ")
            ,DetailTourData("김삿갓 유적지3","","7/9 - PM 14:00",audioList,videoList,37.0794845,128.601784,"010-1234-5678","강원도 영월군 난고 김병연의 유적지","강원도 영월군 하동면 와석리 노루목에 조성된 " +
                    "강원도 영월군 하동면 와석리 노루목에 조성된 난고 김병연(金炳淵)의 유적지. 별호인 김삿갓으로 불리는 난고 김병연(1807~1863)을 기념하는 유적지와 부대시설이 조성되어 있다. 김삿갓 연구자료를 전시하고 있는 난고문학관과 많은 돌탑이 조성되어 있는 묘, 작은 성황당, 마대산을 따라 김삿갓이 살던 집터 등이 있다. ")
        )
       _scheduleList.value = scheduleList
    }

    fun getPosition(position:Int){
        _position.value = position
    }
}