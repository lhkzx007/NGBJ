package com.ngbj.login

import com.ngbj.base.BaseActivity
import com.ngbj.base.BaseFragment
import com.ngbj.login.fragment.AKeyOAuthFragment
import com.ngbj.login.fragment.PasswordLoginFragment
import com.ngbj.login.fragment.PhoneLoginFragment
import com.ngbj.login.fragment.RegisterFragment

/**
 * Created by zack on 2018/8/14
 */
enum class FragmentEnum(val TAG: String) {
    PHONE("phone") {
        override fun create(): BaseFragment {
            return PhoneLoginFragment()
        }
    }
    ,
    PASSWORD("password") {
        override fun create(): BaseFragment {
            return PasswordLoginFragment()
        }

    },
    REGISTER("register") {
        override fun create(): BaseFragment {
            return RegisterFragment()
        }

    },
    A_KEY("a_key") {
        override fun create(): BaseFragment {
            return AKeyOAuthFragment()
        }

    };

    abstract fun create(): BaseFragment

    fun replaceFragment(baseActivity: BaseActivity, id: Int, isBack: Boolean = true) {
        baseActivity.changeFragment(BaseActivity.MSG_FRAGMENT_REPLACE, create(), id, if (isBack) TAG else null)
    }

    fun addFragment(baseActivity: BaseActivity, id: Int, isBack: Boolean = true) {
        baseActivity.changeFragment(BaseActivity.MSG_FRAGMENT_ADD, create(), id, if (isBack) TAG else null)
    }

    fun removeFragment(baseActivity: BaseActivity, id: Int) {
        baseActivity.changeFragment(BaseActivity.MSG_FRAGMENT_REMOVE, create(), id, TAG)
    }
}