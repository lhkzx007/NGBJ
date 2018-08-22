package com.ngbj.home

import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.ngbj.base.BaseActivity
import kotlinx.android.synthetic.main.activity_home.*

/**
 * Created by zack on 2018/8/15
 */
class HomeActivity : BaseActivity() {
    private var count = 0
    private var selectPosition = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
//        home_pager.
        init()
    }

    fun init() {
        addTab("分类")
        addTab("消息")
        addTab("发布", 0, R.layout.home_tab_center)
        addTab("公社")
        addTab("我的")
    }

    fun addTab(name: String, resId: Int = 0, resLayout: Int = R.layout.home_tab_view) {
        val item = layoutInflater.inflate(resLayout, home_tab, false)
        val text = item.findViewById(R.id.tab_text) as TextView
        val icon = item.findViewById(R.id.tab_icon) as ImageView
        text.text = name
        if (resId > 0) {
            icon.setImageResource(resId)
        }
        item.tag = count++
        val params: LinearLayout.LayoutParams = LinearLayout.LayoutParams(item.layoutParams)
        params.width = 0
        params.weight = 1f
        home_tab.addView(item, params)
        item.setOnClickListener {
            val position = it.tag as Int
            selectedItem(position)
            if (position != 2){
                //刷新界面
                text.isSelected = true
                icon.isSelected = true

            }else{
                //跳转到发布页面

            }
        }
    }


    private fun selectedItem(position : Int){

    }
}