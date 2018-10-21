package com.ngbj.home.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ngbj.base.inflate
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.R

/**
 * Created by zack on 2018/10/15
 */
class TabLayout : LinearLayout {
    companion object {
        const val TAG = "TabLayout"
    }
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var count = 0
    private var _tabAdapter: OnTabSelectedListener? = null
    var selectPosition = 0
    private lateinit var selectedView: View
    private var isUserClick = false

    init {
        count = 0
    }

    fun addTab(name: String, resId: Int = 0, int: Int = count++, resLayout: Int = R.layout.home_tab_view) {
        val item = this.inflate(resLayout)
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
        addView(item, params)
        if (item.tag == selectPosition) {
            item.selected(true)
            selectedView = item
        }
        item.setOnClickListener {
            val position = it.tag as Int
            if (position > -1) {
                if (position != selectPosition) {
                    selectedView.selected(false)
                    it.selected(true)
                } else if (!isUserClick){
                    _tabAdapter?.onTabReselected(position)
                }
            }

        }
    }

    private fun View.selected(isSelected: Boolean) {
        val text = this.findViewById(R.id.tab_text) as TextView
        val icon = this.findViewById(R.id.tab_icon) as ImageView
        text.isSelected = isSelected
        icon.isSelected = isSelected
        val position = this.tag as Int
        if (isSelected){
            selectedView = this
            selectPosition = position
        }
        LogUtils.i(TAG,"position $position , isSelected $isSelected , isUserClick :  $isUserClick ")
        if (isSelected) {
            _tabAdapter?.onTabSelected(position)
        } else {
            _tabAdapter?.onTabUnselected(position)
        }
    }

    fun setSelected(position: Int) {
        val view = findViewWithTag(position)
        isUserClick = true
        view?.performClick()
        isUserClick = false
    }


    fun setOnTabSelectedListener(tabAdapter: OnTabSelectedListener) {
        _tabAdapter = tabAdapter
    }


    interface OnTabSelectedListener {
        fun onTabSelected(position: Int)

        fun onTabUnselected(position: Int)

        fun onTabReselected(position: Int)
    }
}