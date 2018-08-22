package com.ngbj

import android.app.Application
import com.ngbj.base.Density

/**
 * Created by zack on 2018/8/9
 */
class MApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        Density.setDensity( this,375f)
    }
}