package com.ngbj.login.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.ngbj.base.BaseActivity
import com.ngbj.base.BaseFragment
import com.ngbj.login.FragmentEnum
import com.ngbj.login.LoginActivity
import com.ngbj.login.R
import kotlinx.android.synthetic.main.fragment_login_phone.*

/**
 *
 * 手机快捷登录
 * Created by zack on 2018/8/3.
 */
class PhoneLoginFragment : BaseFragment() {
    companion object {
        const val TAG = "PhoneLoginFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_phone, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        phone_login_black.setOnClickListener {
//            activity.onBackPressed()
//        }

        phone_login_tv_get_code.setOnClickListener {
            //获取验证码
            Toast.makeText(activity, "获取验证码", Toast.LENGTH_LONG).show()
//            (activity as BaseActivity).changeFragment(BaseActivity.MSG_FRAGMENT_REPLACE,RegisterFragment(), R.id.login_root, "register")
        }

        phone_login_tv_password.setOnClickListener {
            //切换到密码登录
            FragmentEnum.PASSWORD.replaceFragment(activity as BaseActivity, R.id.login_root, false)
        }

        phone_login_tv_akey.setOnClickListener {
            FragmentEnum.A_KEY.replaceFragment(activity as BaseActivity, R.id.login_root, false)
        }

        phone_login_btn_submit.setOnClickListener {
            (activity as LoginActivity).startCertification()
        }
    }
}