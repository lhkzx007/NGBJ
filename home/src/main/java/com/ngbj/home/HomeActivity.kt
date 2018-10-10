package com.ngbj.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ngbj.base.BaseActivity
import com.ngbj.base.BaseFragment
import com.ngbj.base.inflate
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.adapter.ZFragmentPagerAdapter
import com.ngbj.home.frg.MyFragment
import com.ngbj.home.frg.HomeFragment
import com.ngbj.home.frg.ThreeFragment
import com.ngbj.home.frg.TwoFragment
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by zack on 2018/8/15
 */
class HomeActivity : BaseActivity() {
    companion object {
        const val TAG = "HomeActivity"
    }

    private var count = 0
    private var selectPosition = 0
    private var selectedView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        initViewPager()
    }

    private fun initViewPager() {
        val fragments = arrayOf(HomeFragment(), TwoFragment(), ThreeFragment(), MyFragment())
        Log.i(TAG, "${fragments.size}")
        home_pager.adapter = ZFragmentPagerAdapter(supportFragmentManager, fragments)
        home_pager.currentItem = selectPosition
    }

    private fun init() {
        addTab("首页",resId = R.drawable.sel_tab_home)
        addTab("消息",resId = R.drawable.sel_tab_msg)
        addTab("发布", 0, -1, R.layout.home_tab_center)
        addTab("公社",resId = R.drawable.sel_tab_circle)
        addTab("我的",resId = R.drawable.sel_tab_my)
    }

    private fun addTab(name: String, resId: Int = 0, int: Int = count++, resLayout: Int = R.layout.home_tab_view) {
        val item = home_tab.inflate(resLayout)
        val text = item.findViewById(R.id.tab_text) as TextView
        val icon = item.findViewById(R.id.tab_icon) as ImageView
        text.text = name
        if (resId > 0) {
            icon.setImageResource(resId)
        }
        item.tag = int
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(item.layoutParams)
        params.width = 0
        params.weight = 1f
        home_tab.addView(item, params)
        if (item.tag == selectPosition) {
            item.selected(true)
            selectedView = item
        }
        item.setOnClickListener {
            val position = it.tag as Int
            selectedItem(position)
            if (position > -1 && position != selectPosition) {
                selectedView?.selected(false)
                it.selected(true)
                selectedView = it
                selectPosition = position
            }

        }
    }

    fun View.selected(isSelected: Boolean) {
        val text = this.findViewById(R.id.tab_text) as TextView
        val icon = this.findViewById(R.id.tab_icon) as ImageView
        text.isSelected = isSelected
        icon.isSelected = isSelected
    }


    private fun selectedItem(position: Int) {
        LogUtils.i("selected position [$position] ")
        if (position > -1) {
            home_pager.currentItem = position
        } else {
            LogUtils.i("发布信息")
        }
    }

    override fun onBackPressed() {
        val fragment=(home_pager.adapter as ZFragmentPagerAdapter).getItem(selectPosition) as BaseFragment
        if(!fragment.onBackPressed()){
            super.onBackPressed()
        }
    }
}

