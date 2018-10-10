package com.ngbj.home.frg

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.ngbj.base.BaseFragment
import com.ngbj.base.Density
import com.ngbj.base.find
import com.ngbj.base.inflate
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.R
import com.ngbj.home.adapter.FunctionAdapter
import com.ngbj.home.biz.FunctionInfo
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration
import kotlinx.android.synthetic.main.frg_my.*

/**
 * Created by zack on 2018/8/16
 *
 *  页面 - 我的
 */
class MyFragment : BaseFragment() {
    companion object {
        const val TAG = "MyFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_my, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInfos(view)
        initFunctions()
    }

    private fun initInfos(view: View) {
        val infos = listOf(
                FunctionInfo("我的简历", "", ""),
                FunctionInfo("已投递", "", ""),
                FunctionInfo("已发布", "", ""),
                FunctionInfo("收藏", "", ""))

        for (info in infos) {
            val infoV = my_infos.inflate(R.layout.item_my_info)
            (infoV.find(R.id.my_title) as TextView).text = info.title
            Glide.with(context).load(info.icon).into(infoV.find(R.id.my_icon) as ImageView)
            infoV.setOnClickListener {
                LogUtils.i(TAG, " -点击  --${info.title}----- ")
                Snackbar.make(my_rv_functions, "  点击 ${info.title}  action ${info.action} ", Snackbar.LENGTH_LONG).show()
            }
            my_infos.addView(infoV, LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f))
        }
    }

    private fun initFunctions() {
//        DividerItemDecoration(this,DividerItemDecoration.VERTICAL)0xFFCCCCCC
        val id = HorizontalDividerItemDecoration.Builder(context).color(Color.argb(0xff, 0xED, 0xED, 0xED)).size(Density.dp2px(1f).toInt()).build()
        my_rv_functions.addItemDecoration(id)
        my_rv_functions.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        val funs = listOf(
                FunctionInfo("认证", "", ""),
                FunctionInfo("设置", "", ""),
                FunctionInfo("反馈帮助", "", ""),
                FunctionInfo("工资保险", "", "")
        )

        val functionAdapter = FunctionAdapter(funs) { func, i ->
            LogUtils.i(TAG, "  点击 ${func.title}  , position $i")
            Snackbar.make(my_rv_functions, "  点击 ${func.title}  , action ${func.action}", Snackbar.LENGTH_LONG).show();
        }
        my_rv_functions.adapter = functionAdapter
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
    }

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        super.onFragmentVisibleChange(isVisible)
        Log.i(TAG, "  isVisible -> $isVisible")
    }

}