package com.example.travelrecordapp.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.travelrecordapp.BR
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.DetailTourData
import com.example.travelrecordapp.data.EventObserver
import com.example.travelrecordapp.data.MyPlaceData
import com.example.travelrecordapp.data.TourData
import com.example.travelrecordapp.databinding.FragmentHomeBinding
import com.example.travelrecordapp.databinding.FragmentScheduleBinding
import com.example.travelrecordapp.databinding.ItemMyPlaceBinding
import com.example.travelrecordapp.ui.detail.DetailActivity
import com.example.travelrecordapp.ui.home.HomeFragment
import com.example.travelrecordapp.ui.home.HomeViewModel
import com.example.travelrecordapp.util.BaseRecyclerAdapter
import com.example.travelrecordapp.util.VerticalSpaceItemDecoration

class ScheduleFragment : Fragment() {
    private lateinit var binding: FragmentScheduleBinding
    private val viewModel: ScheduleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_schedule, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setRecyclerView()
        viewModel.getScheduleData()

        binding.rvMySchedule.adapter = object:
            BaseRecyclerAdapter<DetailTourData, ItemMyPlaceBinding, ScheduleViewModel>(
            layoutResId = R.layout.item_my_schedule,
            bindingVariableId = BR.DetailTourData,
            viewModel = viewModel
        ){ }
        viewModel.apply {

            finishEvent.observe(viewLifecycleOwner, EventObserver{
                findNavController().navigate(R.id.action_schedule_to_home)
            })
            position.observe(viewLifecycleOwner,  {
                val intent = Intent(requireActivity(),DetailActivity::class.java)
                intent.putExtra("detailTourData", viewModel.scheduleList.value?.get(it))
                startActivity(intent)
            })
        }
        return binding.root
    }

    fun setRecyclerView(){
        val spaceDecoration = VerticalSpaceItemDecoration(100)
        binding.rvMySchedule.addItemDecoration(spaceDecoration)
    }
}
