package com.ngbj.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.ngbj.base.Work
import com.ngbj.base.inflate
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.R
import kotlinx.android.synthetic.main.item_work.view.*

/**
 * Created by zack on 2018/8/27
 */
class WorkAdapter(private val items: List<Work>, private val listener: (Work, Int) -> Unit) : RecyclerView.Adapter<WorkAdapter.WorkHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = WorkHolder(parent.inflate(R.layout.item_work), listener)
    override fun onBindViewHolder(holder: WorkHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size

    class WorkHolder(itemView: View, private val listener: (Work, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Work) = with(itemView) {
            work_name.text = item.name
            work_reward.text = item.reward
            work_conditions.text = item.conditions
            work_release_time.text = item.releaseTime
            work_publisher.text = item.publisher
            work_tag.text = item.tag
            itemView.setOnClickListener {
                listener(item, adapterPosition)
            }
        }
    }
}


