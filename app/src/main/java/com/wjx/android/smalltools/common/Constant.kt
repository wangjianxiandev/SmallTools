package com.wjx.android.smalltools.common

import android.os.Environment
import com.wjx.android.smalltools.R
import com.wjx.android.smalltools.bean.MainData
import java.io.File

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/5/12 18:13
 */
val MAIN_LIST = mutableListOf(
    MainData(R.drawable.ic_fan, "反编译"),
    MainData(R.drawable.ic_current_activity, "当前Activity"),
    MainData(R.drawable.ic_device_info, "本机信息")
)

val BASE_PATH =
    "${Environment.getExternalStorageDirectory().path}${File.separator}Box${File.separator}"
val APK_PATH = "${BASE_PATH}apk${File.separator}"
val REVERSE_PATH = "${BASE_PATH}reverse${File.separator}"
const val SHOW_WINDOW = "show_window"
const val SHOW_LOG = "show_log"
