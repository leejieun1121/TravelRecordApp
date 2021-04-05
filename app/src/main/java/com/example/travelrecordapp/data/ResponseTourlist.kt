package com.example.travelrecordapp.data

data class ResponseTourlist (
        val data : TourList,
        val success : Boolean,
        val message : String
        )

data class TourList(
        val useridx:String,
        val tourlist : List<TourData>
)

data class TourData (
        val tourimg: String,
        val tourspotname : String,
        val tourbegindate:String,
        val tourbegintime:String,
        val tourhour : String
        )
