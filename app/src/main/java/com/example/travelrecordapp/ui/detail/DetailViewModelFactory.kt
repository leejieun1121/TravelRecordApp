package com.example.travelrecordapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.travelrecordapp.data.DetailTourData
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val detailData:DetailTourData):ViewModelProvider.Factory {
    //SchduleFragment 에서 넘겨받은 데이터 뷰모델에 넘기려고 만듦
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DetailViewModel::class.java)){
            return DetailViewModel(detailData) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}