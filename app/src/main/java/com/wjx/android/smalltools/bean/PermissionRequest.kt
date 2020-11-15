package com.wjx.android.smalltools.bean

import com.wjx.android.smalltools.common.ext.KtxPermissionFragment

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/11/16 0:11
 */
data class PermissionRequest(
    val permissionFragment: KtxPermissionFragment,
    val permissions: List<String>,
    val requestCode: Int
) {

    fun retry() {
        permissionFragment.requestPermissionsByFragment(permissions.toTypedArray(), requestCode)
    }
}