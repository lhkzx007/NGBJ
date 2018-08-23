package com.ngbj.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.util.Log


/**
 * Created by zack on 2018/8/2.
 */
open class BaseActivity : FragmentActivity() {
    companion object {
        const val TAG = "BaseActivity"
        const val MSG_FRAGMENT_REMOVE = 10000
        const val MSG_FRAGMENT_ADD = 10001
        const val MSG_FRAGMENT_REPLACE = 10002
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //-------
        Log.i(TAG, " onCreate ")
    }

    open public fun changeFragment(what: Int, fragment: Fragment, frameId: Int, backStackTag: String? = null) {
        when (what) {
            MSG_FRAGMENT_ADD -> {
                addFragment(fragment, frameId, backStackTag)
            }
            MSG_FRAGMENT_REPLACE -> {
                replaceFragment(fragment, frameId, backStackTag)
            }
        }
    }


}

