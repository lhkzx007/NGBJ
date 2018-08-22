package com.ngbj.login.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import com.ngbj.base.BaseActivity
import com.ngbj.base.BaseFragment
import com.ngbj.base.Density
import com.ngbj.login.FragmentEnum
import com.ngbj.login.LoginActivity
import com.ngbj.login.R
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by zack on 2018/8/3
 */
class AKeyOAuthFragment : BaseFragment(), CompoundButton.OnCheckedChangeListener {


    companion object {
        const val TAG = "AKeyOAuthFragment"
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_tv_phone.setOnClickListener { switchOtherLogin(FragmentEnum.PHONE) }
        login_tv_password.setOnClickListener { switchOtherLogin(FragmentEnum.PASSWORD) }
        login_btn_phone_submit.setOnClickListener { submit() }
        login_rb_protocol.isChecked = false
        login_rb_protocol.setOnCheckedChangeListener(this)
        onCheckedChanged(login_rb_protocol, false)
        login_rb_protocol.compoundDrawablePadding = Density.dp2px(5f).toInt()
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.i(TAG, "   is checked [$isChecked]")
        login_btn_phone_submit.isEnabled = isChecked
        val drawable: Drawable = resources.getDrawable(if (isChecked) R.mipmap.login_check_on else R.mipmap.login_check_off)
        drawable.setBounds(0, 0, Density.dp2px(14f).toInt(), Density.dp2px(14f).toInt())
        login_rb_protocol.setCompoundDrawables(drawable, null, null, null)
    }


    /**
     * 切换到其他登录方式
     */
    fun switchOtherLogin(type: FragmentEnum) {
        Log.i(TAG, "switch Other login , current login type is < a key OAuth > ")
        type.replaceFragment(activity as BaseActivity, R.id.login_root, false)
    }


    /**
     * 提交登录
     */
    fun submit() {
        Log.i(TAG, "submit login , start auth")
        Toast.makeText(this.activity, "登录成功", Toast.LENGTH_LONG).show()
        (activity as LoginActivity).startCertification()
    }


}