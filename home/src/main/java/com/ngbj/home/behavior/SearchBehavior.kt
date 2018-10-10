package com.ngbj.home.behavior

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.design.widget.CoordinatorLayout
import android.support.v4.graphics.drawable.DrawableCompat
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.RelativeLayout
import com.ngbj.home.R

/**
 * 首页SearchBar动画效果
 */
class SearchBehavior:CoordinatorLayout.Behavior<View> {
    private val mMarginLeft:Int
    private val mMarginRight:Int
    private var mHeight:Float
    private var mExpend:Boolean = true
    private var mContext:Context
    private var mSet: AutoTransition
    private val mAnimDuration:Long = 300
    @TargetApi(Build.VERSION_CODES.KITKAT)
    constructor(ctx: Context, attributeSet: AttributeSet):super(ctx,attributeSet){
        this.mMarginLeft = ctx.resources.getDimension(R.dimen.search_margin_left).toInt()
        this.mMarginRight = ctx.resources.getDimension(R.dimen.search_margin_right).toInt()
        this.mHeight = Utils.getScrollHeight(ctx)
        mContext = ctx
        mSet = AutoTransition()
        mSet.duration = mAnimDuration
    }

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View): Boolean {
        return dependency.id == R.id.home_fl_head
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View): Boolean {
        if(child is ViewGroup){
            //-0.5f滑动慢，最后一下没监听到
            val currY = dependency.translationY- 0.5f
            val alpha = Utils.range(0f,1f,Math.abs(currY)/mHeight*2)
            //背景颜色
//            child.setBackgroundColor(Color.argb(alpha.toInt()*255,255,255,255))

//            val expend = Math.abs(currY) >= mHeight
//            //箭头展示,动画结束后显示
//            val ivArrow = child.getChildAt(0)
//            if(!expend){
//                ivArrow.visibility = View.GONE
//            }else{
//                ivArrow.postDelayed({ivArrow.visibility = View.VISIBLE },mAnimDuration)
//            }
//            toggle(child,expend,false)
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

    fun toggle(targetView: ViewGroup, expend:Boolean, force:Boolean){
        if(expend != mExpend||force){
            this.mExpend = expend
            val height = targetView.height
            if(height == 0 && !force){
                targetView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener{
                    override fun onPreDraw(): Boolean {
                        targetView.viewTreeObserver.removeOnPreDrawListener(this)
                        toggle(targetView,expend,true)
                        return true
                    }
                })
            }
//            if(expend) expand(targetView.getChildAt(1) as CardView) else reduce(targetView.getChildAt(1) as CardView)
        }
    }
    private fun expand(targetView: ViewGroup){
        val layoutParams = targetView.layoutParams as RelativeLayout.LayoutParams
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT
        layoutParams.setMargins(mMarginLeft, 0, mMarginRight, 0)
        targetView.layoutParams = layoutParams
        beginDelayedTransition(targetView)
    }

    private fun reduce(targetView: ViewGroup) {
        val layoutParams = targetView.layoutParams as RelativeLayout.LayoutParams
        layoutParams.width = RelativeLayout.LayoutParams.MATCH_PARENT
        layoutParams.setMargins(mMarginLeft - mMarginLeft*2/3,0, mMarginRight, 0)
        targetView.layoutParams = layoutParams
        beginDelayedTransition(targetView)
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    private fun beginDelayedTransition(view: ViewGroup) {
        TransitionManager.beginDelayedTransition(view, mSet)
    }
}