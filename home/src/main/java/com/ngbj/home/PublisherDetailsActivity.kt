package com.ngbj.home

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import com.ngbj.base.BaseActivity
import com.ngbj.base.find
import com.ngbj.base.utils.LogUtils
import kotlinx.android.synthetic.main.activity_publisher.*

/**
 * Created by zack on 2018/10/16
 */
class PublisherDetailsActivity : BaseActivity(), ViewPager.OnPageChangeListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_publisher)
        initView()
    }

    private fun initView() {
        initTab()
        initPage()
    }

    private fun initTab() {
        newTab("基本信息", true)
        newTab("在招职位", false)
        newTab("认证信息", false)
        publisher_tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                LogUtils.i(" onTabReselected ")
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                LogUtils.i(" onTabUnselected ")
                setTabSelect(tab, false)
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                LogUtils.i(" onTabSelected position ${tab?.position}")
                tab?.let {
                    publisher_page.setCurrentItem(it.position, true)
                    setTabSelect(tab, true)
                    when (it.position) {
                        0 -> {
                            LogUtils.i(TAG, " 1  ")
                        }
                        1 -> {
                            LogUtils.i(TAG, " 2  ")
                        }
                        2 -> {
                            LogUtils.i(TAG, " 3  ")
                        }
                    }
                }

            }
        })

    }

    private fun newTab(title: String, isSelected: Boolean) {
        val tab = publisher_tab.newTab().setCustomView(R.layout.home_tab_switch)
        val tv = (tab.customView!!.find(R.id.tab_switch_title) as TextView)
        tv.text = title
        setTabSelect(tab, isSelected)
        publisher_tab.addTab(tab, isSelected)
    }

    private fun setTabSelect(tab: TabLayout.Tab?, isSelected: Boolean) {
        val tv = (tab?.customView!!.find(R.id.tab_switch_title) as TextView)
        tv.isSelected = isSelected
        tab.customView!!.find(R.id.tab_switch_in).visibility = if (isSelected) View.VISIBLE else View.INVISIBLE
    }

    private fun initPage() {
        publisher_page.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(publisher_tab))
    }

    override fun onPageScrollStateChanged(state: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageSelected(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}