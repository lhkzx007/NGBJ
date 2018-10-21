package com.ngbj.home

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.ngbj.base.BaseActivity
import com.ngbj.base.Work
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.adapter.WorkAdapter
import kotlinx.android.synthetic.main.activity_work_details.*

/**
 * Created by zack on 2018/8/29
 */
class WorkDetailsActivity :BaseActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_work_details)
        initView()
    }

    /**
     *
     */
    fun startToPublisher(view :View){
        startActivity(Intent(this,PublisherDetailsActivity::class.java))
    }

    fun initView(){
        work_ry_recommend.isFocusable = false
        work_ry_recommend.setHasFixedSize(true)
        work_ry_recommend.isNestedScrollingEnabled = false
        work_ry_recommend.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        val works=listOf(Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张全蛋","个人 短期 自家",false)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张傻蛋","个人 短期 自家",true)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张铁蛋","个人 短期 自家",false)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","深圳全傻蛋有限公司","公司 长期 哈",true))
        work_ry_recommend.adapter = WorkAdapter(works){ work: Work, poistion: Int ->
            LogUtils.i(" $poistion :  $work  ")
//            startActivity(Intent(this, WorkDetailsActivity::class.java))
        }
    }

}