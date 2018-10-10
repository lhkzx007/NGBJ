package com.ngbj.home.frg

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.TabItem
import android.support.design.widget.TabLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ngbj.base.*
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.R
import com.ngbj.home.WorkDetailsActivity
import com.ngbj.home.adapter.WorkAdapter
import com.ngbj.home.behavior.HeadBehavior
import kotlinx.android.synthetic.main.frg_home.*
import kotlinx.android.synthetic.main.home_tab_switch.view.*

/**
 * Created by zack on 2018/8/16
 */
class HomeFragment : BaseFragment() {
    private lateinit var mHeaderBehavior: HeadBehavior

    companion object {
        const val TAG = "HomeFragment"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_home, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTab()

        home_filter_recommend.setOnClickListener { filterClick(it) }
        home_filter_location.setOnClickListener { filterClick(it) }
        home_filter_classify.setOnClickListener { filterClick(it) }
        home_filter_requirements.setOnClickListener { filterClick(it) }

        mHeaderBehavior = (home_fl_head.layoutParams as CoordinatorLayout.LayoutParams).behavior as HeadBehavior
        initList()
    }

    private fun initList() {

        val works = listOf(Work("泥瓦工", "1000/天", "北京 通州 22-30岁", "8月11日", "", "张全蛋", "个人 短期 自家", false)
                , Work("泥瓦工", "1000/天", "北京 通州 22-30岁", "8月11日", "", "张二蛋", "个人 短期 自家", true)
                , Work("泥瓦工", "1000/天", "北京 通州 22-30岁", "8月11日", "", "张二蛋", "个人 短期 自家", true)
                , Work("泥瓦工", "1000/天", "北京 通州 22-30岁", "8月11日", "", "张二蛋", "个人 短期 自家", true)
                , Work("泥瓦工", "1000/天", "北京 通州 22-30岁", "8月11日", "", "张二蛋", "个人 短期 自家", true)
                , Work("泥瓦工", "1000/天", "北京 通州 22-30岁", "8月11日", "", "张二蛋", "个人 短期 自家", true)
                , Work("泥瓦工", "1000/天", "北京 通州 22-30岁", "8月11日", "", "张傻蛋", "个人 短期 自家", true)
                , Work("泥瓦工", "1000/天", "北京 通州 22-30岁", "8月11日", "", "张铁蛋", "个人 短期 自家", false)
                , Work("泥瓦工", "1020/天", "北京 通州 22-30岁", "8月11日", "", "深圳全傻蛋有限公司", "公司 长期 哈", true))

        home_ry_works.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        home_ry_works.adapter = WorkAdapter(works) { work: Work, poistion: Int ->
            LogUtils.i(" $poistion :  $work  ")
            startActivity(Intent(activity, WorkDetailsActivity::class.java))
        }
    }

    private fun initTab() {
        newTab("找工作", true)
        newTab("找工人", false)
        home_switch_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                LogUtils.i(" onTabReselected ")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                LogUtils.i(" onTabUnselected ")
                setTabSelect(tab, false)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                LogUtils.i(" onTabSelected position ${tab?.position}")
                setTabSelect(tab, true)
                when (tab?.position) {
                    0 -> {
                        LogUtils.i("找工作")
                    }
                    1 -> {
                        LogUtils.i("找工人")
                    }
                }
            }
        })

    }

    private fun newTab(title: String, isSelected: Boolean) {
        val tab = home_switch_tab.newTab().setCustomView(R.layout.home_tab_switch)
        val tv = (tab.customView!!.find(R.id.tab_switch_title) as TextView)
        tv.text = title
        setTabSelect(tab, isSelected)
        home_switch_tab.addTab(tab, isSelected)
    }

    private fun setTabSelect(tab: TabLayout.Tab?, isSelected: Boolean) {
        val tv = (tab?.customView!!.find(R.id.tab_switch_title) as TextView)
        tv.isSelected = isSelected
        tab.customView!!.find(R.id.tab_switch_in).visibility = if (isSelected) View.VISIBLE else View.INVISIBLE
    }

    override fun onBackPressed(): Boolean {
        if (mHeaderBehavior.isClose()) {
//            nsv.scrollTo(0,0)
            mHeaderBehavior.scrollToOpen()
            return true
        } else {
            return false
        }
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        LogUtils.i("   first  visible")
    }

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        super.onFragmentVisibleChange(isVisible)
        Log.i(TAG, "  isVisible -> $isVisible")
    }

    private fun filterClick(view: View) {
        val item = view as TextView
        LogUtils.i("click id(${item.id})  ${item.text}")

        when (item) {
            home_filter_recommend -> LogUtils.i("show pop")
            home_filter_location -> LogUtils.i("跳转到定位页面")
            home_filter_requirements -> LogUtils.i("显示要求弹窗")
            home_filter_classify -> LogUtils.i("显示分类弹窗")
        }
    }

}

