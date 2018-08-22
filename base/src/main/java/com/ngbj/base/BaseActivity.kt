package com.ngbj.base

import android.os.Bundle
import android.os.Message
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.util.Log




/**
 * Created by zack on 2018/8/2.
 */
open class BaseActivity : FragmentActivity() {
    companion object {
        const val TAG = "BaseActivity"
        const val MSG_FRAGMENT_REMOVE = 10000
        const val MSG_FRAGMENT_ADD =  10001
        const val MSG_FRAGMENT_REPLACE = 10002
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //-------
        Log.i(TAG, " onCreate ")
    }

    open public fun changeFragment(what:Int, fragment: Fragment, frameId: Int, backStackTag: String? = null){
        when(what){
            MSG_FRAGMENT_ADD -> {
                addFragment( fragment,frameId ,backStackTag) }
            MSG_FRAGMENT_REPLACE -> {
                replaceFragment(fragment,frameId ,backStackTag)
            }
        }
    }


    /**
     * 扩展FragmentManager的方法
     */

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }

    fun FragmentActivity.addFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
        supportFragmentManager.inTransaction {
            add(frameId, fragment)
            backStackTag?.let { addToBackStack(fragment.javaClass.name) }
        }
    }

    fun FragmentActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
        supportFragmentManager.inTransaction {
            replace(frameId, fragment)
            backStackTag?.let { addToBackStack(fragment.javaClass.name) }
        }
    }

    fun FragmentActivity.removeFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            remove(fragment)
        }
    }


}

