package com.ngbj

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.ngbj.base.BaseActivity
import com.ngbj.base.Utils.getPhoneNumber
import com.ngbj.home.HomeActivity
import com.ngbj.login.LoginActivity

class LauncherActivity : BaseActivity() {

    private lateinit var phoneNumber: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launcher)
        phoneNumber = getPhoneNumber(this)
        mHandler.sendEmptyMessageDelayed(0, 5000)
    }


    // 创建一个Handler

    /**
     * 延时启动
     */
    private val mHandler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            val intent = Intent(this@LauncherActivity, LoginActivity::class.java)
            intent.putExtra(LoginActivity.KEY_PHONE_NUMBER, phoneNumber)
            startActivity(intent)
            finish()
        }

    }
}