package com.wjx.android.smalltools.common.ext

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/11/16 0:11
 */
class KtxPermissionFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun requestPermissionsByFragment(permissions: Array<String>, requestCode: Int) {
        requestPermissions(permissions, requestCode)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val neverAskPermissions = mutableListOf<String>()
        val deniedPermissions = mutableListOf<String>()
        val grantedPermissions = mutableListOf<String>()
        permissions.forEachIndexed { index, permission ->
            if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                if (shouldShowRequestPermissionRationale(permission)) {
                    deniedPermissions.add(permission)
                } else {
                    neverAskPermissions.add(permission)
                }
            } else {
                grantedPermissions.add(permission)
            }
        }

        val permissionsCallback = PermissionsMap.get(requestCode)
        if (deniedPermissions.isNotEmpty()) {
            // denied
            permissionsCallback?.onDenied(deniedPermissions)
        }

        if (neverAskPermissions.isNotEmpty()) {
            // never ask
            permissionsCallback?.onNeverAskAgain(neverAskPermissions)
        }

        if (deniedPermissions.isEmpty() && neverAskPermissions.isEmpty()) {
            // granted
            permissionsCallback?.onGranted()
        }
    }
}