package com.ngbj.home.behavior

import android.content.Context
import android.graphics.Rect
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewCompat
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup

/**
 * Created by zack on 2018/9/5
 */
abstract class HeaderScrollingViewBehavior(context: Context, attrs: AttributeSet) : ViewOffsetBehavior(context, attrs) {
    private val mTempRect1 = Rect()
    private val mTempRect2 = Rect()

    private var mVerticalLayoutGap = 0
    private var mOverlayTop: Int = 0

    override fun onMeasureChild(parent: CoordinatorLayout?, child: View,
                                parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int,
                                heightUsed: Int): Boolean {
        val childLpHeight = child.layoutParams.height
        if (childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT || childLpHeight == ViewGroup.LayoutParams.WRAP_CONTENT) {
            // If the menu's height is set to match_parent/wrap_content then measure it
            // with the maximum visible height

            val dependencies = parent!!.getDependencies(child)
            val header = findFirstDependency(dependencies)
            if (header != null) {
                if (ViewCompat.getFitsSystemWindows(header) && !ViewCompat.getFitsSystemWindows(child)) {
                    // If the header is fitting system windows then we need to also,
                    // otherwise we'll get CoL's compatible measuring
                    ViewCompat.setFitsSystemWindows(child, true)

                    if (ViewCompat.getFitsSystemWindows(child)) {
                        // If the set succeeded, trigger a new layout and return true
                        child.requestLayout()
                        return true
                    }
                }

                if (ViewCompat.isLaidOut(header)) {
                    var availableHeight = View.MeasureSpec.getSize(parentHeightMeasureSpec)
                    if (availableHeight == 0) {
                        // If the measure spec doesn't specify a size, use the current height
                        availableHeight = parent.height
                    }

                    val height = availableHeight - header.measuredHeight + getScrollRange(header)
                    val heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(height,
                            if (childLpHeight == ViewGroup.LayoutParams.MATCH_PARENT)
                                View.MeasureSpec.EXACTLY
                            else
                                View.MeasureSpec.AT_MOST)

                    // Now measure the scrolling view with the correct height
                    parent.onMeasureChild(child, parentWidthMeasureSpec,
                            widthUsed, heightMeasureSpec, heightUsed)

                    return true
                }
            }
        }
        return false
    }

    override fun layoutChild(parent: CoordinatorLayout?, child: View?, layoutDirection: Int) {
        val dependencies = parent!!.getDependencies(child)
        val header = findFirstDependency(dependencies)
        if (header != null) {
            val lp = child!!.layoutParams as CoordinatorLayout.LayoutParams
            val available = mTempRect1
            available.set(parent.paddingLeft + lp.leftMargin,
                    header.bottom + lp.topMargin,
                    parent.width - parent.paddingRight - lp.rightMargin,
                    parent.height + header.bottom
                            - parent.paddingBottom - lp.bottomMargin)

            val out = mTempRect2
            GravityCompat.apply(resolveGravity(lp.gravity), child.measuredWidth,
                    child.measuredHeight, available, out, layoutDirection)

            val overlap = getOverlapPixelsForOffset(header)

            child.layout(out.left, out.top - overlap, out.right, out.bottom - overlap)
            mVerticalLayoutGap = out.top - header.bottom
        } else {
            // If we don't have a dependency, let super handle it
            super.layoutChild(parent, child, layoutDirection)
            mVerticalLayoutGap = 0
        }
    }

    internal fun getOverlapRatioForOffset(header: View): Float {
        return 1f
    }

    internal fun getOverlapPixelsForOffset(header: View): Int {
        return if (mOverlayTop == 0)
            0
        else
            Utils.constrain(Math.round(getOverlapRatioForOffset(header) * mOverlayTop),
                    0, mOverlayTop)

    }

    private fun resolveGravity(gravity: Int): Int {
        return if (gravity == Gravity.NO_GRAVITY) GravityCompat.START or Gravity.TOP else gravity
    }

    abstract fun findFirstDependency(views: List<View>): View?

    protected fun getScrollRange(v: View): Int {
        return v.measuredHeight
    }

    /**
     * The gap between the top of the scrolling view and the bottom of the header layout in pixels.
     */
    internal fun getVerticalLayoutGap(): Int {
        return mVerticalLayoutGap
    }

    /**
     * Set the distance that this view should overlap any [AppBarLayout].
     *
     * @param overlayTop the distance in px
     */
    fun setOverlayTop(overlayTop: Int) {
        mOverlayTop = overlayTop
    }

    /**
     * Returns the distance that this view should overlap any [AppBarLayout].
     */
    fun getOverlayTop(): Int {
        return mOverlayTop
    }
}