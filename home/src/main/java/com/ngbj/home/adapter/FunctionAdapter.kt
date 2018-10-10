package com.ngbj.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ngbj.base.Work
import com.ngbj.base.inflate
import com.ngbj.home.R
import com.ngbj.home.biz.FunctionInfo
import kotlinx.android.synthetic.main.item_my_function.view.*
import kotlinx.android.synthetic.main.item_work.view.*

/**
 * Created by zack on 2018/8/27
 */
class FunctionAdapter(private val items: List<FunctionInfo>, private val listener: (FunctionInfo, Int) -> Unit) : RecyclerView.Adapter<FunctionAdapter.FunctionInfoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FunctionInfoHolder(parent.inflate(R.layout.item_my_function), listener)
    override fun onBindViewHolder(holder: FunctionInfoHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size

    class FunctionInfoHolder(itemView: View, private val listener: (FunctionInfo, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: FunctionInfo) = with(itemView) {
            function_title.text = item.title
            Glide.with(itemView).load(item.icon).into(function_icon)
            itemView.setOnClickListener {
                listener(item, adapterPosition)
            }
        }
    }
}


