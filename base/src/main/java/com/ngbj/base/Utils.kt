package com.ngbj.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.telephony.TelephonyManager
import android.text.TextUtils
import android.util.Log

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
}


