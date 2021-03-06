package com.ngbj.home.behavior

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.os.Parcelable
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.WindowInsetsCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Scroller
import com.ngbj.base.utils.LogUtils
import com.ngbj.home.behavior.Utils.range

/**
 * Created by zack on 2018/9/3
 */
class HeadBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {
    private val mScroller: Scroller = Scroller(context)
    private val mHeight = Utils.getScrollHeight(context!!)
    private var mChildView: View? = null
    private var axes: Int = 0
    private var velocityY: Float = 0f
    private var mScrollRunnable: ScrollerRunnable? = null
    private var mStartHeaderHidden: Boolean = false

    /**
     *  当coordinatorLayout 的子View试图开始嵌套滑动的时候被调用。当返回值为true的时候表明
     *  coordinatorLayout 充当nested scroll parent 处理这次滑动，需要注意的是只有当返回值为true
     *  的时候，Behavior 才能收到后面的一些nested scroll 事件回调（如：onNestedPreScroll、onNestedScroll等）
     *  这个方法有个重要的参数nestedScrollAxes，表明处理的滑动的方向。
     *
     * @param coordinatorLayout 和Behavior 绑定的View的父CoordinatorLayout
     * @param child  和Behavior 绑定的View
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes 嵌套滑动 应用的滑动方向，看 {@link ViewCompat#SCROLL_AXIS_HORIZONTAL},
     *                         {@link ViewCompat#SCROLL_AXIS_VERTICAL}
     * @return
     */
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: View, directTargetChild: View, target: View, nestedScrollAxes: Int): Boolean {
        this.mChildView = child
        this.axes = nestedScrollAxes
        return (axes == ViewCompat.SCROLL_AXIS_VERTICAL) && !isClose()
    }

    /**
     *  嵌套滚动结束时被调用，这是一个清除滚动状态等的好时机。
     * @param coordinatorLayout
     * @param child
     * @param target
     */
    override fun onStopNestedScroll(coordinatorLayout: CoordinatorLayout?, child: View?, target: View?) {
        super.onStopNestedScroll(coordinatorLayout, child, target)
    }

    /**
     * 用户松开手指并且会发生惯性动作之前调用，参数提供了速度信息，可以根据这些速度信息
     * 决定最终状态，比如滚动Header，是让Header处于展开状态还是折叠状态。返回true 表
     * 示消费了fling.
     *
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param velocityX x 方向的速度
     * @param velocityY y 方向的速度
     * @return
     */
    override fun onNestedFling(coordinatorLayout: CoordinatorLayout, child: View, target: View, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        this.velocityY = velocityY
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout, child: View, ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> {
                LogUtils.i(" ACTION_DOWN ")
                mStartHeaderHidden = isClose()
            }
            MotionEvent.ACTION_MOVE -> {
                LogUtils.i(" ACTION_MOVE ")
                if (isClose() && !mStartHeaderHidden) parent.onStartNestedScroll(parent, child, axes)
            }
            MotionEvent.ACTION_UP -> {
                LogUtils.i(" ACTION_UP ")
                handlerActionUp()
            }
        }
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    /**
     * 当被依赖的View 状态（如：位置、大小）发生变化时，这个方法被调用
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return super.onDependentViewChanged(parent, child, dependency)
    }

    /**
     * 嵌套滚动发生之前被调用
     * 在nested scroll child 消费掉自己的滚动距离之前，嵌套滚动每次被nested scroll child
     * 更新都会调用onNestedPreScroll。注意有个重要的参数consumed，可以修改这个数组表示你消费
     * 了多少距离。假设用户滑动了100px,child 做了90px 的位移，你需要把 consumed［1］的值改成90，
     * 这样coordinatorLayout就能知道只处理剩下的10px的滚动。
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dx  用户水平方向的滚动距离
     * @param dy  用户竖直方向的滚动距离
     * @param consumed
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout, child: View, target: View?, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed)
        //降低移动距离，防止抖动,ScrollFriction太大还是会抖动。。。。
        val tempDy = dy.toFloat() / (Utils.getScrollFriction() * 2)
        child.translationY = range(-mHeight, 0f, child.translationY - tempDy)
        consumed[1] = dy
    }

    /**
     * 进行嵌套滚动时被调用
     * @param coordinatorLayout
     * @param child
     * @param target
     * @param dxConsumed target 已经消费的x方向的距离
     * @param dyConsumed target 已经消费的y方向的距离
     * @param dxUnconsumed x 方向剩下的滚动距离
     * @param dyUnconsumed y 方向剩下的滚动距离
     */
    override fun onNestedScroll(coordinatorLayout: CoordinatorLayout?, child: View?, target: View?, dxConsumed: Int, dyConsumed: Int, dxUnconsumed: Int, dyUnconsumed: Int) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
    }

    /**
     * 表示是否给应用了Behavior 的View 指定一个依赖的布局，通常，当依赖的View 布局发生变化时
     * 不管被被依赖View 的顺序怎样，被依赖的View也会重新布局
     * @param parent
     * @param child 绑定behavior 的View
     * @param dependency   依赖的view
     * @return 如果child 是依赖的指定的View 返回true,否则返回false
     */
    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onApplyWindowInsets(coordinatorLayout: CoordinatorLayout?, child: View?, insets: WindowInsetsCompat?): WindowInsetsCompat {
        return super.onApplyWindowInsets(coordinatorLayout, child, insets)
    }

    /**
     * onStartNestedScroll返回true才会触发这个方法，接受滚动处理后回调，可以在这个
     * 方法里做一些准备工作，如一些状态的重置等。
     * @param coordinatorLayout
     * @param child
     * @param directTargetChild
     * @param target
     * @param nestedScrollAxes
     */
    override fun onNestedScrollAccepted(coordinatorLayout: CoordinatorLayout?, child: View?, directTargetChild: View?, target: View?, nestedScrollAxes: Int) {
        super.onNestedScrollAccepted(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)
    }

    //可以重写这个方法对子View 进行重新布局
    override fun onLayoutChild(parent: CoordinatorLayout?, child: View?, layoutDirection: Int): Boolean {
        return super.onLayoutChild(parent, child, layoutDirection)
    }

    private fun createRunnable() {
        LogUtils.i(" childView  $mChildView")
        if (mScrollRunnable == null && mChildView!=null )
            mScrollRunnable = ScrollerRunnable(mScroller, mChildView!!, (mHeight).toInt())
    }

    private fun handlerActionUp() {
        createRunnable()
        if (velocityY > 1000) {
            mScrollRunnable?.scrollToClose()
            return
        }
        if (mChildView!=null && Math.abs(mChildView!!.translationY) < (mHeight / 2)) {
            mScrollRunnable?.scrollToOpen()
        } else {
            mScrollRunnable?.scrollToClose()
        }
    }

    open fun isClose(): Boolean {
        return mChildView != null && mChildView!!.translationY.toInt() <= -mHeight.toInt()
    }

    open fun scrollToOpen() {
        createRunnable()
        mScrollRunnable?.scrollToOpen()
    }
}