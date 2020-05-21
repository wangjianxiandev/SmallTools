package com.wjx.android.smalltools.module

import android.app.Application
import android.content.Context
import com.wjx.android.smalltools.common.SPreference
import kotlin.properties.Delegates

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/5/21 19:38
 */
class App : Application() {
    companion object {
        lateinit var CONTEXT : App
    }

    override fun onCreate() {
        super.onCreate()
        SPreference.setContext(applicationContext)
        CONTEXT = this
    }
}