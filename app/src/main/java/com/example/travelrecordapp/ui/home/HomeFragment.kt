package com.example.travelrecordapp.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.travelrecordapp.BR
import com.example.travelrecordapp.R
import com.example.travelrecordapp.data.MyPlaceData
import com.example.travelrecordapp.data.TourData
import com.example.travelrecordapp.databinding.FragmentHomeBinding
import com.example.travelrecordapp.databinding.ItemMyPlaceBinding
import com.example.travelrecordapp.ui.login.LoginViewModel
import com.example.travelrecordapp.util.BaseRecyclerAdapter
import com.example.travelrecordapp.util.VerticalSpaceItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.getTourList()

        setSpinner()
        setRecyclerView()


        binding.rvMyPlace.adapter = object:BaseRecyclerAdapter<TourData, ItemMyPlaceBinding, HomeViewModel>(
                layoutResId = R.layout.item_my_place,
                bindingVariableId = BR.TourData,
                viewModel = viewModel
        ){ }

        viewModel.apply {
            position.observe(viewLifecycleOwner,  {
                //TODO 해당 position의 id 넘겨서 SCHEDULE Fragment 로 이동(해당 TourData의 schedule로 넘어가게)
                findNavController().navigate(R.id.action_home_to_schedule)
            })
        }
        return binding.root
    }

    private fun setRecyclerView(){
        val spaceDecoration = VerticalSpaceItemDecoration(80)
        binding.rvMyPlace.addItemDecoration(spaceDecoration)
    }

    private fun setSpinner(){
        val items = resources.getStringArray(R.array.myplace_sort_array)
        val spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_dropdown_item, items)

        binding.spinnerMyplaceSort.adapter = spinnerAdapter
        binding.spinnerMyplaceSort.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when(position){
                    0 -> {
                        //TODO 최신순
                        //TODO 클라에서 처리할것인지, 정렬을 ymd 어느거 기준으로 해야할까
                    }
                    else -> {
                        //TODO 과거순

                    }
                }

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // TODO 최신순
            }

        }

    }

}