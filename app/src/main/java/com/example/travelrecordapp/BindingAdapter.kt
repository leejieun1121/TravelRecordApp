package com.example.travelrecordapp

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

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