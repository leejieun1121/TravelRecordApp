package com.example.travelrecordapp.data.source

import com.example.travelrecordapp.data.RequestTourList
import com.example.travelrecordapp.data.ResponseTourlist
import com.example.travelrecordapp.data.repository.TourRepository
import javax.inject.Inject

interface TourDataSource {
    fun getTourList(useridx:RequestTourList,callback: TourRepository.GetDataCallback<ResponseTourlist>)
}