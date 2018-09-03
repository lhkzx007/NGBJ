package com.ngbj.home.behavior

import android.content.Context
import android.os.Parcelable
import android.support.design.widget.CoordinatorLayout
import android.support.v4.view.WindowInsetsCompat
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * Created by zack on 2018/9/3
 */
class HeadBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {
    init {

    }

    //可以重写这个方法对子View 进行重新布局
    override fun onLayoutChild(parent: CoordinatorLayout?, child: View?, layoutDirection: Int): Boolean {
        return super.onLayoutChild(parent, child, layoutDirection)
    }

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
    override fun onStartNestedScroll(coordinatorLayout: CoordinatorLayout?, child: View?, directTargetChild: View?, target: View?, nestedScrollAxes: Int): Boolean {
        return super.onStartNestedScroll(coordinatorLayout, child, directTargetChild, target, nestedScrollAxes)
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
    override fun onNestedFling(coordinatorLayout: CoordinatorLayout?, child: View?, target: View?, velocityX: Float, velocityY: Float, consumed: Boolean): Boolean {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed)
    }

    override fun onInterceptTouchEvent(parent: CoordinatorLayout?, child: View?, ev: MotionEvent?): Boolean {
        return super.onInterceptTouchEvent(parent, child, ev)
    }

    /**
     * 当被依赖的View 状态（如：位置、大小）发生变化时，这个方法被调用
     * @param parent
     * @param child
     * @param dependency
     * @return
     */
    override fun onDependentViewChanged(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
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
    override fun onNestedPreScroll(coordinatorLayout: CoordinatorLayout?, child: View?, target: View?, dx: Int, dy: Int, consumed: IntArray?) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed)
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

    override fun onDependentViewRemoved(parent: CoordinatorLayout?, child: View?, dependency: View?) {
        super.onDependentViewRemoved(parent, child, dependency)
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

    override fun onTouchEvent(parent: CoordinatorLayout?, child: View?, ev: MotionEvent?): Boolean {
        return super.onTouchEvent(parent, child, ev)
    }

    override fun getScrimColor(parent: CoordinatorLayout?, child: View?): Int {
        return super.getScrimColor(parent, child)
    }

    override fun onMeasureChild(parent: CoordinatorLayout?, child: View?, parentWidthMeasureSpec: Int, widthUsed: Int, parentHeightMeasureSpec: Int, heightUsed: Int): Boolean {
        return super.onMeasureChild(parent, child, parentWidthMeasureSpec, widthUsed, parentHeightMeasureSpec, heightUsed)
    }

    override fun isDirty(parent: CoordinatorLayout?, child: View?): Boolean {
        return super.isDirty(parent, child)
    }

    override fun onNestedPreFling(coordinatorLayout: CoordinatorLayout?, child: View?, target: View?, velocityX: Float, velocityY: Float): Boolean {
        return super.onNestedPreFling(coordinatorLayout, child, target, velocityX, velocityY)
    }

    override fun onRestoreInstanceState(parent: CoordinatorLayout?, child: View?, state: Parcelable?) {
        super.onRestoreInstanceState(parent, child, state)
    }

    override fun blocksInteractionBelow(parent: CoordinatorLayout?, child: View?): Boolean {
        return super.blocksInteractionBelow(parent, child)
    }

    override fun onSaveInstanceState(parent: CoordinatorLayout?, child: View?): Parcelable {
        return super.onSaveInstanceState(parent, child)
    }

    override fun getScrimOpacity(parent: CoordinatorLayout?, child: View?): Float {
        return super.getScrimOpacity(parent, child)
    }
}