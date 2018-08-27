package com.ngbj.base.utils

import android.util.Log

/**
 * Created by zack on 2018/8/27
 */
class LogUtils {
    companion object {
        /**
         * 得到tag（所在类.方法（L:行））
         * @return
         */
        fun generateTag(tag: String): String {
            val ste = Thread.currentThread().stackTrace[4]
            val className = ste.className.substring(ste.className.lastIndexOf(".") + 1)
            return "$tag: $className.${ste.methodName}[${ste.lineNumber}]"
        }

        fun i(tag: String, msg: String) {
            Log.i(generateTag(tag), msg)
        }

        fun w(tag: String, msg: String) {
            Log.w(generateTag(tag), msg)
        }

        fun d(tag: String, msg: String) {
            Log.d(generateTag(tag), msg)
        }

        fun e(tag: String, msg: String) {
            Log.e(generateTag(tag), msg)
        }

        fun v(tag: String, msg: String) {
            Log.v(generateTag(tag), msg)
        }

        fun wtf(tag: String, msg: String) {
            Log.wtf(generateTag(tag), msg)
        }


        //------------------------------------------------------------
        fun i(msg: String) {
            Log.i(generateTag("LogUtils"), msg)
        }

        fun w(msg: String) {
            Log.w(generateTag("LogUtils"), msg)
        }

        fun d(msg: String) {
            Log.d(generateTag("LogUtils"), msg)
        }

        fun e(msg: String) {
            Log.e(generateTag("LogUtils"), msg)
        }

        fun v(msg: String) {
            Log.v(generateTag("LogUtils"), msg)
        }

        fun wtf(msg: String) {
            Log.wtf(generateTag("LogUtils"), msg)
        }


    }


}