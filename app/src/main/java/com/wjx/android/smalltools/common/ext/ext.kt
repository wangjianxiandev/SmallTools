package com.wjx.android.smalltools.common.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Rect
import android.os.Build
import android.provider.Settings
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.wjx.android.smalltools.bean.PermissionRequest
import java.io.Closeable

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/5/21 18:41
 */
fun RecyclerView.itemPadding(padding: Int) {
    addItemDecoration(
        PaddingItemDecoration(
            padding,
            padding,
            padding,
            padding
        )
    )
}

class PaddingItemDecoration(top: Int, bottom: Int, left: Int, right: Int) :
    RecyclerView.ItemDecoration() {

    private val mTop = top
    private val mBottom = bottom
    private val mLeft = left
    private val mRight = right

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = mBottom
        outRect.top = mTop
        outRect.left = mLeft
        outRect.right = mRight
    }
}

fun Context.goToAccessibilitySetting() =
    Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).run { startActivity(this) }

const val TAG = "ktx"

fun FragmentActivity.request(vararg permissions: String) {
    ActivityCompat.requestPermissions(this, permissions, 0XFF)
}

/**
 * request permissions list in [permissions]
 * @param callbacks DSL callback to handle request result
 */
fun FragmentActivity.request(
    vararg permissions: String,
    callbacks: PermissionsCallbackDSL.() -> Unit
) {

    val permissionsCallback = PermissionsCallbackDSL().apply { callbacks() }
    val requestCode = PermissionsMap.put(permissionsCallback)

    val needRequestPermissions = permissions.filter { !isGranted(it) }

    if (needRequestPermissions.isEmpty()) {
        permissionsCallback.onGranted()
    } else {
        val shouldShowRationalePermissions = mutableListOf<String>()
        val shouldNotShowRationalePermissions = mutableListOf<String>()
        for (permission in needRequestPermissions) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission))
                shouldShowRationalePermissions.add(permission)
            else
                shouldNotShowRationalePermissions.add(permission)
        }

        if (shouldShowRationalePermissions.isNotEmpty()) {
            permissionsCallback.onShowRationale(
                PermissionRequest(
                    getKtxPermissionFragment(
                        this
                    ),
                    shouldShowRationalePermissions,
                    requestCode
                )
            )
        }


        if (shouldNotShowRationalePermissions.isNotEmpty()) {
            getKtxPermissionFragment(this).requestPermissionsByFragment(
                shouldNotShowRationalePermissions.toTypedArray(),
                requestCode
            )
        }
    }
}

private fun getKtxPermissionFragment(activity: FragmentActivity): KtxPermissionFragment {
    var fragment = activity.supportFragmentManager.findFragmentByTag(TAG)
    if (fragment == null) {
        fragment = KtxPermissionFragment()
        activity.supportFragmentManager.beginTransaction().add(fragment, TAG).commitNow()
    }
    return fragment as KtxPermissionFragment
}


fun Activity.isGranted(permission: String): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun close(vararg args: Closeable) {
    for (c in args) c.close()
}