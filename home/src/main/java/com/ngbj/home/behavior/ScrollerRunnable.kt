package com.ngbj.home.behavior

import android.annotation.TargetApi
import android.os.Build
import android.view.View
import android.widget.Scroller
import com.ngbj.base.utils.LogUtils

/**
 * Created by zack on 2018/9/5
 */
class ScrollerRunnable(
        private var scroller: Scroller,
        private var childView: View,
        private var height: Int) : Runnable {
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    open fun scrollToOpen() {
        val scrollY = childView.translationY.toInt()
        LogUtils.i(" scrollY -> $scrollY ")
        scroller.startScroll(0, scrollY, 0, -scrollY)
        startScroll()
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    open fun scrollToClose() {
        val currY = childView.translationY.toInt()
        val scrollY = height - Math.abs(currY)
        LogUtils.i(" scrollY -> $scrollY ")
        scroller.startScroll(0, currY, 0, -scrollY)
        startScroll()
    }

    private fun startScroll() {
        if (scroller.computeScrollOffset()) {
            childView.postDelayed(this, 16)
        }
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun run() {
        if (scroller.computeScrollOffset()) {
            childView.translationY = scroller.currY.toFloat()
            LogUtils.i(" childView.translationY  -> ${childView.translationY}")
            childView.postDelayed(this, 16)
        }
    }
}