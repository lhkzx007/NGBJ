package com.ngbj.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.support.design.widget.TabLayout
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.LinearLayout
import com.ngbj.base.utils.LogUtils

/**
 * Created by zack on 2018/8/3
 */
object Utils {
    @SuppressLint("MissingPermission", "HardwareIds")
    fun getPhoneNumber(context: Context): String {
        val phoneService: TelephonyManager
        try {
            phoneService = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            Log.i("getPhoneNumber", " phone number ${phoneService.line1Number}")
//            if (!TextUtils.isEmpty(phoneService.line1Number)){
//                context.getSharedPreferences("phone",Context.MODE_PRIVATE)
//            }
            return phoneService.line1Number
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""

    }

    /**
     * 获取本应用的设置信息
     */
    fun getOptionsSP(context: Context) : SharedPreferences{
        return context.getSharedPreferences("options",Context.MODE_PRIVATE)
    }

    fun setIndicatorWidth(tabLayout: TabLayout , marginLeft: Int ,marginRight:Int){
        try {
            val tClass = tabLayout.javaClass
            val mTabStripF = tClass.getDeclaredField("mTabStrip")
            mTabStripF.isAccessible = true
            val layout : LinearLayout =mTabStripF.get(tabLayout) as LinearLayout
            for (i  in 0  until layout.childCount){
                LogUtils.i("  index --- $i")
                val child = layout.getChildAt(i)
                child.setPadding(0,0,0,0)
                val params = LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1f)
                params.leftMargin = marginLeft
                params.rightMargin = marginRight
                child.layoutParams = params
                child.invalidate()
            }

        }catch (e :  Exception){
            e.printStackTrace()
        }
    }


//    private void setUpIndicatorWidth(TabLayout tabLayout, int marginLeft, int marginRight) {
//        Class<?> tabLayoutClass = tabLayout.getClass();
//        Field tabStrip = null;
//        try {
//            tabStrip = tabLayoutClass.getDeclaredField("mTabStrip");
//            tabStrip.setAccessible(true);
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
//
//        LinearLayout layout = null;
//        try {
//            if (tabStrip != null) {
//                layout = (LinearLayout) tabStrip.get(tabLayout);
//            }
//            for (int i = 0; i < layout.getChildCount(); i++) {
//                View child = layout.getChildAt(i);
//                child.setPadding(0, 0, 0, 0);
//                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
//                    params.setMarginStart(DensityUtil.dp2px(getActivity(), marginLeft));
//                    params.setMarginEnd(DensityUtil.dp2px(getActivity(), marginRight));
//                }
//                child.setLayoutParams(params);
//                child.invalidate();
//            }
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
}


