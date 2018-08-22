package com.ngbj.login.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ngbj.base.BaseActivity
import com.ngbj.base.BaseFragment
import com.ngbj.login.FragmentEnum
import com.ngbj.login.LoginActivity
import com.ngbj.login.R
import kotlinx.android.synthetic.main.fragment_login_password.*

/**
 *
 *  密码登录
 * Created by zack on 2018/8/7
 */
class PasswordLoginFragment : BaseFragment() {
    companion object {
        const val TAG = "PasswordLoginFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        password_login_black.setOnClickListener {
//            activity.onBackPressed()
//        }

        password_login_btn_submit.setOnClickListener {
            //登录提交
            (activity as LoginActivity).startCertification()
        }

        password_login_tv_register.setOnClickListener {
            //跳转到注册页
            FragmentEnum.REGISTER.replaceFragment(activity as BaseActivity,R.id.login_root)
//            (activity as BaseActivity).changeFragment(BaseActivity.MSG_FRAGMENT_REPLACE,FragmentEnum.REGISTER.create(), R.id.login_root, FragmentEnum.REGISTER.TAG)
        }

        password_login_tv_phone.setOnClickListener {
            //切换到手机快捷登录
            FragmentEnum.PHONE.replaceFragment(activity as BaseActivity,R.id.login_root,false)
            Log.i(TAG, "switch phone  login , current login type is < $TAG > ")
//            (activity as BaseActivity).changeFragment(BaseActivity.MSG_FRAGMENT_REPLACE,FragmentEnum.PHONE.create(), R.id.login_root, FragmentEnum.PHONE.TAG)
        }

        password_login_tv_akey.setOnClickListener {
            //切换到手机快捷登录
            Log.i(TAG, "switch phone  login , current login type is < $TAG > ")
//            (activity as BaseActivity).changeFragment(BaseActivity.MSG_FRAGMENT_REPLACE,FragmentEnum.A_KEY.create(), R.id.login_root, FragmentEnum.A_KEY.TAG)
            FragmentEnum.A_KEY.replaceFragment(activity as BaseActivity,R.id.login_root,false)
        }


    }


}