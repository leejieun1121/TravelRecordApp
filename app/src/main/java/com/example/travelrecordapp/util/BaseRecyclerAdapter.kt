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


abstract class BaseRecyclerAdapter<ITEM:Any, VDB:ViewDataBinding> (
    @LayoutRes private val layoutResId: Int,
    private val bindingVariableId: Int? = null,
) : RecyclerView.Adapter<BaseRecyclerAdapter.BaseRecyclerViewHolder<VDB>>(){

//    abstract val listener: OnItemClickListener?

    private val items = mutableListOf<ITEM>()

    fun replaceAll(items: List<ITEM>?) {
        items?.let {
            this.items.run {
                clear()
                addAll(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= object: BaseRecyclerViewHolder<VDB>(
        layoutResId = layoutResId,
        parent = parent,
        bindingVariableId = bindingVariableId
    ) { }

    override fun onBindViewHolder(holder: BaseRecyclerViewHolder<VDB>, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    abstract class BaseRecyclerViewHolder<VDB:ViewDataBinding>(
        @LayoutRes layoutResId: Int,
        parent: ViewGroup,
        private val bindingVariableId: Int?

        ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(layoutResId,parent,false)
        ){
        protected val binding: VDB = DataBindingUtil.bind(itemView)!!

        fun bind(item : Any?){
            try {
                bindingVariableId?.let {
                    binding.setVariable(it, item)
                    Log.d("datadata",item.toString())
//                    dataBinding.root.setOnClickListener {
//                        listener?.onItemClicked(item, adapterPosition)
//                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            binding.executePendingBindings()

        }
    }

    interface OnItemClickListener {
        fun onItemClicked(item: Any?, position:Int?)
    }

}

