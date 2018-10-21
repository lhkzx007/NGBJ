package com.ngbj.home.frg

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ngbj.base.BaseFragment
import com.ngbj.base.inflate
import com.ngbj.home.R
import com.ngbj.home.adapter.MessageAdapter
import com.ngbj.home.biz.MessageInfo
import com.ngbj.home.biz.UserInfo
import kotlinx.android.synthetic.main.frg_messages.*

/**
 * Created by zack on 2018/8/16
 */
class MessagesFragment : BaseFragment() {

    companion object {
        const val TAG = "MessagesFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.frg_messages)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        messages_list.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val u1 = UserInfo("11", "000000", "张全蛋", 28, 1, "", "18819812345", "token", arrayOf(0, 0, 0))
        val u2 = UserInfo("12", "000000", "张二蛋", 28, 1, "", "18819812345", "token", arrayOf(0, 0, 0))
        val u3 = UserInfo("13", "000000", "张三蛋", 28, 1, "", "18819812345", "token", arrayOf(0, 0, 0))
        val u4 = UserInfo("14", "000000", "张四蛋", 28, 1, "", "18819812345", "token", arrayOf(0, 0, 0))
        val data = listOf(
                MessageInfo(u1, "8:20", "之前写代码时，都没有注意singleLine已经废弃，每次想让TextView或Edittext单行显示都是直接使用，但是这样其实不好，因为废弃的函数可能在有的手机上出现问题，所以需要自己去找到替换的函数。一般Google都会给废弃的函数提供替换的函数，阿里巴巴的代码规范也要求程序员自己查找替换的函数。所以在网上查找了资料，最终解决了这个问题。这里记录一下。 ", "001"),
                MessageInfo(u2, "9:20", "一.EditText中singleLine过期替代方法", "001"),
                MessageInfo(u3, "9:25", "替代方法 ,网上讲了很多,其中最简单就是在xml里加上", "001"),
                MessageInfo(u4, "9:27", "对于上面的每个权限，又存在两种情况，一个是只是可访问，另一种是可授权，例如对于“查看用户”这个权限，如果用户只被授予“可访问”，那么他就不能将他所具有的这个权限分配给其他人", "001")
        )
        messages_list.adapter = MessageAdapter(data) { messageInfo: MessageInfo, i: Int ->
            Snackbar.make(view, "position[$i] : ${messageInfo.send_message} ", Snackbar.LENGTH_LONG).show()
        }
    }


    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
    }

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        super.onFragmentVisibleChange(isVisible)
        Log.i(TAG, "  isVisible -> $isVisible")
    }

}