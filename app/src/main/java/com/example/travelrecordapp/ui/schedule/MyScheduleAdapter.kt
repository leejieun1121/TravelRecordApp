package com.example.travelrecordapp.ui.schedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.travelrecordapp.data.MyPlaceData
import com.example.travelrecordapp.databinding.ItemMyPlaceBinding
import com.example.travelrecordapp.databinding.ItemMyScheduleBinding
import com.example.travelrecordapp.databinding.ItemMyScheduleBindingImpl

class MyScheduleAdapter(private val list : MutableList<MyPlaceData>,listener:scheduleItemClick) : RecyclerView.Adapter<ViewHolder>(){
    val listener = listener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMyScheduleBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item,listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ViewHolder(private var binding : ItemMyScheduleBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(item : MyPlaceData,listener:scheduleItemClick){
        binding.myPlace = item
        binding.executePendingBindings()
        binding.imgPlace.setOnClickListener {
            listener.scheduleItemClick(adapterPosition)
        }

    }
}