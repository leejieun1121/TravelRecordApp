package com.example.travelrecordapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelrecordapp.data.MyPlaceData
import com.example.travelrecordapp.data.TourData
import com.example.travelrecordapp.databinding.ItemMyPlaceBinding

class MyPlaceAdapter(private val list : MutableList<TourData>) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMyPlaceBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)

    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ViewHolder(private var binding :ItemMyPlaceBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item : TourData){
        binding.tourData = item
        binding.executePendingBindings()

    }
}