package com.ngbj.login

import android.content.Intent
import android.os.Bundle
import com.ngbj.base.BaseActivity
import com.ngbj.base.addFragment
import com.ngbj.login.fragment.AKeyOAuthFragment

/**
 * Created by zack on 2018/8/2
 */
class LoginActivity : BaseActivity() {
    companion object {
        const val TAG = "LoginActivity"
        const val KEY_PHONE_NUMBER = "phone_number"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        initView()
    }

    private fun initView() {
        addFragment(AKeyOAuthFragment(), R.id.login_root)
    }


    fun startCertification() {
        startActivity(Intent(this@LoginActivity, CertificationActivity::class.java))
        finish()
    }
}