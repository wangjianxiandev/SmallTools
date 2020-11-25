package com.wjx.android.smalltools.common

import android.content.Context
import android.content.pm.PackageInfo

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/11/25 23:30
 */
object CommonUtils {
    fun getAppName(context: Context, packageInfo: PackageInfo): String {
        return packageInfo.applicationInfo.loadLabel(context.packageManager).toString()
    }
}