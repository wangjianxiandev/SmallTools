package com.wjx.android.smalltools.module

import android.app.Application
import android.content.Context
import com.wjx.android.library.Leoric
import com.wjx.android.library.LeoricConfigs
import com.wjx.android.smalltools.common.SPreference
import com.wjx.android.smalltools.keeplive.*

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

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Leoric.init(
            base, LeoricConfigs(
                LeoricConfigs.LeoricConfig(
                    "$packageName:resident",
                    Service1::class.java.getCanonicalName(),
                    Receiver1::class.java.getCanonicalName(),
                    Activity1::class.java.getCanonicalName()
                ),
                LeoricConfigs.LeoricConfig(
                    "android.media",
                    Service2::class.java.getCanonicalName(),
                    Receiver2::class.java.getCanonicalName(),
                    Activity2::class.java.getCanonicalName()
                )
            )
        )
    }
}