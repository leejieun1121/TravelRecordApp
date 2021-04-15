package com.example.travelrecordapp

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.travelrecordapp.data.TourData
import com.example.travelrecordapp.util.BaseRecyclerAdapter

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl:String?){
    imgUrl?.let {
        Glide.with(imageView.context)
            .load(imgUrl)
            .apply(RequestOptions())
            .placeholder(R.drawable.loading_image)
            .error(R.drawable.ic_broken_image)
            .into(imageView)
    }
}

@BindingAdapter("setTourTime")
fun setTourTime(textView: TextView,tourData: TourData?){
    if(tourData!!.tourbegintime.split(" ")[0]=="오전"){
        textView.text = tourData!!.tourbegindate.replace("-","/") + " - " +"AM"+tourData!!.tourbegintime.split(" ")[1].replace("시",":00")
    }else{
        textView.text = tourData!!.tourbegindate.replace("-","/") + " - " +"PM"+tourData!!.tourbegintime.split(" ")[1].replace("시",":00")
    }
}

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    (this.adapter as? BaseRecyclerAdapter<Any, *,*>)?.run {
        replaceAll(list)
        notifyDataSetChanged()
    }
}