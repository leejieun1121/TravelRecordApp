package com.example.travelrecordapp.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.travelrecordapp.BR


abstract class BaseRecyclerAdapter<ITEM:Any, VDB:ViewDataBinding, VM:ViewModel> (
    @LayoutRes private val layoutResId: Int,
    private val bindingVariableId: Int? = null,
    private val viewModel : ViewModel,

    ) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerViewHolder<VDB,VM>>(){

    private val items = mutableListOf<ITEM>()

    fun replaceAll(items: List<ITEM>?) {
        items?.let {
            this.items.run {
                clear()
                addAll(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= object: BaseRecyclerViewHolder<VDB,VM>(
        layoutResId = layoutResId,
        parent = parent,
        bindingVariableId = bindingVariableId,
        viewModel = viewModel
    ) { }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<VDB,VM>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    abstract class BaseRecyclerViewHolder<VDB:ViewDataBinding,VM:ViewModel>(
        @LayoutRes layoutResId: Int,
        parent: ViewGroup,
        private val bindingVariableId: Int?,
        private val viewModel : ViewModel,

        ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutResId,parent,false)
        ){
        protected val binding: VDB = DataBindingUtil.bind(itemView)!!

        fun bind(item : Any?){
            try {
                bindingVariableId?.let {
                    binding.setVariable(it, item)
                    binding.setVariable(BR.viewModel, viewModel as VM)
                    binding.setVariable(BR.itemPosition,adapterPosition)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.executePendingBindings()

        }
    }
}

