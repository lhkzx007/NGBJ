package com.ngbj.home.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ngbj.base.inflate
import com.ngbj.home.R
import com.ngbj.home.biz.MessageInfo
import kotlinx.android.synthetic.main.item_messages.view.*

/**
 * Created by zack on 2018/8/27
 */
class MessageAdapter(private val items: List<MessageInfo>, private val listener: (MessageInfo, Int) -> Unit) : RecyclerView.Adapter<MessageAdapter.MessageHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MessageHolder(parent.inflate(R.layout.item_messages), listener)
    override fun onBindViewHolder(holder: MessageHolder, position: Int) = holder.bind(items[position])
    override fun getItemCount() = items.size

    class MessageHolder(itemView: View, private val listener: (MessageInfo, Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MessageInfo) = with(itemView) {
            messages_name.text = item.sender.user_name
            Glide.with(context).load(item.sender.user_avatar).into(messages_avatar)
            messages_time.text = item.send_time
            messages_new_info.text = item.send_message
//            work_name.text = item.name
//            work_reward.text = item.reward
//            work_conditions.text = item.conditions
//            work_release_time.text = item.releaseTime
//            work_publisher.text = item.publisher
//            work_tag.text = item.tag
            itemView.setOnClickListener {
                listener(item, adapterPosition)
            }
        }
    }
}


