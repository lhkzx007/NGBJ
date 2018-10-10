package com.ngbj.home.behavior

import android.content.Context
import android.util.TypedValue
import com.ngbj.base.Density
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.R

/**
 * Created by zack on 2018/9/5
 */
object Utils {
    fun constrain(amount: Int, low: Int, high: Int): Int {
        return if (amount < low) low else if (amount > high) high else amount
    }

    fun constrain(amount: Float, low: Float, high: Float): Float {
        return if (amount < low) low else if (amount > high) high else amount
    }

    //内容部分在头部滚动的范围
    fun getScrollHeight(ctx: Context): Float {
        val mHeight = ctx.resources.getDimension(R.dimen.head_height)
        val mSHeight = ctx.resources.getDimension(R.dimen.search_height)
        return (mHeight - mSHeight) / getScrollFriction()
    }

    //给定内容部分和头部滚动位移的一个倍数，
    fun getScrollFriction(): Float = 1.5f

//    fun getActionBarHeight(ctx: Context): Int {
//        var actionBarHeight = 0
//        val tv = TypedValue()
//        if (ctx.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
//            actionBarHeight = TypedValue.complexToDimensionPixelSize(tv.data, ctx.resources.displayMetrics)
//        }
//        return actionBarHeight
//    }
//
//    fun getStatusBarHeight(ctx: Context): Int {
//        var result = 0
//        val resourceId = ctx.resources.getIdentifier("status_bar_height", "dimen", "android")
//        if (resourceId > 0) {
//            result = ctx.resources.getDimensionPixelSize(resourceId)
//        }
//        return result
//    }

    fun range(min: Float, max: Float, value: Float): Float {
        return Math.min(Math.max(min, value), max)
    }
}