package com.ngbj.home.behavior

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

/**
 * Created by zack on 2018/9/5
 */
open class ViewOffsetBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context,attrs) {
    private var mViewOffsetHelper: ViewOffsetHelper? = null

    private var mTempTopBottomOffset = 0
    private var mTempLeftRightOffset = 0



    override fun onLayoutChild(parent: CoordinatorLayout?, child: View?, layoutDirection: Int): Boolean {
        // First let lay the child out
        layoutChild(parent, child, layoutDirection)

        if (mViewOffsetHelper == null) {
            mViewOffsetHelper = ViewOffsetHelper(child)
        }
        mViewOffsetHelper!!.onViewLayout()

        if (mTempTopBottomOffset != 0) {
            mViewOffsetHelper!!.topAndBottomOffset = mTempTopBottomOffset
            mTempTopBottomOffset = 0
        }
        if (mTempLeftRightOffset != 0) {
            mViewOffsetHelper!!.leftAndRightOffset = mTempLeftRightOffset
            mTempLeftRightOffset = 0
        }

        return true
    }

    protected open fun layoutChild(parent: CoordinatorLayout?, child: View?, layoutDirection: Int) {
        // Let the parent lay it out by default
        parent!!.onLayoutChild(child!!, layoutDirection)
    }

    fun setTopAndBottomOffset(offset: Int): Boolean {
        if (mViewOffsetHelper != null) {
            return mViewOffsetHelper!!.setTopAndBottomOffset(offset)
        } else {
            mTempTopBottomOffset = offset
        }
        return false
    }

    fun setLeftAndRightOffset(offset: Int): Boolean {
        if (mViewOffsetHelper != null) {
            return mViewOffsetHelper!!.setLeftAndRightOffset(offset)
        } else {
            mTempLeftRightOffset = offset
        }
        return false
    }

    fun getTopAndBottomOffset(): Int {
        return if (mViewOffsetHelper != null) mViewOffsetHelper!!.topAndBottomOffset else 0
    }

    fun getLeftAndRightOffset(): Int {
        return if (mViewOffsetHelper != null) mViewOffsetHelper!!.leftAndRightOffset else 0
    }
}