package com.ngbj.home.biz

/**
 * Created by zack on 2018/10/17
 */
data class MessageInfo(val sender: UserInfo,
                       val send_time: String,
                       val send_message: String,
                       val message_id: String
)