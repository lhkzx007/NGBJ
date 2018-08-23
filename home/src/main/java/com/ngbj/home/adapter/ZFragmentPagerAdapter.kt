package com.ngbj.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ngbj.base.BaseFragment

/**
 * Created by zack on 2018/8/23
 */
class ZFragmentPagerAdapter(fm: FragmentManager?, fs: Array<BaseFragment>) : FragmentPagerAdapter(fm) {
    private val fragments: Array<BaseFragment> = fs

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}