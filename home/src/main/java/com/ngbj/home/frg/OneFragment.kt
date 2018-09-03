package com.ngbj.home.frg

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.ngbj.base.BaseFragment
import com.ngbj.base.Density
import com.ngbj.base.Work
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.R
import com.ngbj.home.WorkDetailsActivity
import com.ngbj.home.adapter.WorkAdapter
import com.ngbj.home.biz.WorkInfo
import kotlinx.android.synthetic.main.frg_one.*

/**
 * Created by zack on 2018/8/16
 */
class OneFragment : BaseFragment() {
    companion object {
        const val TAG = "OneFragment"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frg_one,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frg_text.text = TAG


        one_filter_recommend.setOnClickListener { filterClick(it) }
        one_filter_location.setOnClickListener { filterClick(it) }
        one_filter_classify.setOnClickListener { filterClick(it) }
        one_filter_requirements.setOnClickListener { filterClick(it) }



        one_ry_works.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        one_ry_works.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun getItemOffsets(outRect: Rect?, view: View?, parent: RecyclerView?, state: RecyclerView.State?) {
                outRect?.set(0,0,0,Density.dp2px(10f).toInt())
            }
        })

        val works=listOf(Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张全蛋","个人 短期 自家",false)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张二蛋","个人 短期 自家",true)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张二蛋","个人 短期 自家",true)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张二蛋","个人 短期 自家",true)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张二蛋","个人 短期 自家",true)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张二蛋","个人 短期 自家",true)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张傻蛋","个人 短期 自家",true)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","张铁蛋","个人 短期 自家",false)
                , Work("泥瓦工","1000/天","北京 通州 22-30岁" ,"8月11日","","深圳全傻蛋有限公司","公司 长期 哈",true))
        one_ry_works.adapter = WorkAdapter(works){work: Work, poistion: Int ->
            LogUtils.i(" $poistion :  $work  ")
            startActivity(Intent(activity, WorkDetailsActivity::class.java))
        }
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        LogUtils.i("   first  visible")
    }

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        super.onFragmentVisibleChange(isVisible)
        Log.i(TAG,"  isVisible -> $isVisible")
    }

    private fun filterClick(view: View){
        val item = view as TextView
        LogUtils.i("click id(${item.id})  ${item.text}")

        when(item){
            one_filter_recommend ->  LogUtils.i("show pop")
            one_filter_location ->  LogUtils.i("跳转到定位页面")
            one_filter_requirements -> LogUtils.i("显示要求弹窗")
            one_filter_classify -> LogUtils.i("显示分类弹窗")
        }
    }

}