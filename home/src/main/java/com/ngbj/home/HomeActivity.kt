package com.ngbj.home

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.view.ViewPager
import android.util.Log
import com.ngbj.base.BaseActivity
import com.ngbj.base.BaseFragment
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.adapter.ZFragmentPagerAdapter
import com.ngbj.home.frg.MyFragment
import com.ngbj.home.frg.HomeFragment
import com.ngbj.home.frg.ThreeFragment
import com.ngbj.home.frg.MessagesFragment
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by zack on 2018/8/15
 */
class HomeActivity : BaseActivity(), ViewPager.OnPageChangeListener {

    companion object {
        const val TAG = "HomeActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        init()
        initViewPager()
    }

    private fun initViewPager() {
        val fragments = arrayOf(HomeFragment(), MessagesFragment(), ThreeFragment(), MyFragment())
        Log.i(TAG, "${fragments.size}")
        home_pager.adapter = ZFragmentPagerAdapter(supportFragmentManager, fragments)
        home_pager.currentItem = 0
        home_pager.addOnPageChangeListener(this)
    }

    private fun init() {
        home_tab.setOnTabSelectedListener(object :com.ngbj.home.widget.TabLayout.OnTabSelectedListener{
            override fun onTabSelected(position: Int) {
                LogUtils.i(TAG," ---onTabSelected- $position")
                home_pager.currentItem = position
            }

            override fun onTabUnselected(position: Int) {
                LogUtils.i(TAG," ---onTabUnselected-  $position")
            }

            override fun onTabReselected(position: Int) {
                LogUtils.i(TAG," ---onTabReselected-  $position")
            }

        })

        home_tab.addTab("首页", resId = R.drawable.sel_tab_home)
        home_tab.addTab("消息", resId = R.drawable.sel_tab_msg)
        home_tab.addTab("", 0, -1)
        home_tab.addTab("公社", resId = R.drawable.sel_tab_circle)
        home_tab.addTab("我的", resId = R.drawable.sel_tab_my)

        home_tab_center.setOnClickListener {
            LogUtils.i("发布信息")
            Snackbar.make(home_pager, " 发布信息 ", Snackbar.LENGTH_LONG).show()
        }
    }

    /* page OnPageChangeListener  */
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {
        LogUtils.i(TAG, " selectPosition ${home_tab.selectPosition}   ,  position $position")
        home_tab.setSelected(position)
    }
    /* end page OnPageChangeListener  */

    override fun onBackPressed() {
        val fragment = (home_pager.adapter as ZFragmentPagerAdapter).getItem(home_tab.selectPosition) as BaseFragment
        if (!fragment.onBackPressed()) {
            super.onBackPressed()
        }
    }
}

