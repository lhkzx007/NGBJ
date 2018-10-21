package com.ngbj.home.frg

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ngbj.home.R

/**
 * Created by zack on 2018/10/15
 */
class HomeDialogFragment : DialogFragment() {


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view= LayoutInflater.from(context).inflate(R.layout.home_tab_center,null)

        return AlertDialog.Builder(context).setView(view).create()

    }

}