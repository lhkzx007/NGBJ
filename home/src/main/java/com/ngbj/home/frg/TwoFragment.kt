package com.ngbj.home.frg

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ngbj.base.BaseFragment
import com.ngbj.base.inflate
import com.ngbj.home.R
import kotlinx.android.synthetic.main.frg_two.*

/**
 * Created by zack on 2018/8/16
 */
class TwoFragment : BaseFragment() {

    companion object {
        const val TAG = "TwoFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.frg_two)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        frg_text.text = TAG
    }

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
    }

    override fun onFragmentVisibleChange(isVisible: Boolean) {
        super.onFragmentVisibleChange(isVisible)
        Log.i(TAG, "  isVisible -> $isVisible")
    }

}