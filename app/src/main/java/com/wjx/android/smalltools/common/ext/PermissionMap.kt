package com.wjx.android.smalltools.common.ext

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/11/16 0:10
 */
internal object PermissionsMap {

    private val atomicInteger = AtomicInteger(100)

    private val map = mutableMapOf<Int, PermissionsCallback>()

    fun put(callbacks: PermissionsCallback): Int {
        return atomicInteger.getAndIncrement().also {
            map[it] = callbacks
        }
    }

    fun get(requestCode: Int): PermissionsCallback? {
        return map[requestCode].also {
            map.remove(requestCode)
        }
    }

}