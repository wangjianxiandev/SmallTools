package com.wjx.android.smalltools.module.activity

import android.app.AlertDialog
import com.lzf.easyfloat.permission.PermissionUtils
import com.wjx.android.smalltools.R
import com.wjx.android.smalltools.common.SHOW_WINDOW
import com.wjx.android.smalltools.common.SPreference
import com.wjx.android.smalltools.common.ext.goToAccessibilitySetting
import com.wjx.android.smalltools.module.currentactivity.BoxAccessibilityService
import com.wjx.android.smalltools.module.currentactivity.FloatWindowManager
import kotlinx.android.synthetic.main.activity_current.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class CurrentActivity : BaseActivity() {
    private var windowEnabled by SPreference(SHOW_WINDOW, true)
    override fun getLayoutId() = R.layout.activity_current

    override fun initView() {
        checkPermission()
        initToolbar()
    }

    override fun initData() {
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        windowSwitch.isChecked = windowEnabled
        accessibilitySwitch.isChecked = BoxAccessibilityService.instance != null
        accessibilitySwitch.setOnCheckedChangeListener { _, _ -> goToAccessibilitySetting() }
        windowSwitch.setOnCheckedChangeListener { _, isChecked -> updateWindow(isChecked) }
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.title = getString(R.string.current_activity)
    }

    private fun updateWindow(isOpen: Boolean) {
        windowEnabled = isOpen
        if (isOpen) {
            if (!FloatWindowManager.hasInit()) FloatWindowManager.init(this)
            FloatWindowManager.show()
        } else {
            FloatWindowManager.hide()
        }
    }

    /**
     * 检测浮窗权限是否开启，若没有给与申请提示框（非必须，申请依旧是EasyFloat内部内保进行）
     */
    private fun checkPermission() {

        if (PermissionUtils.checkPermission(this)) {
            if (windowEnabled) FloatWindowManager.init(this)
        } else {
            AlertDialog.Builder(this)
                .setMessage("使用悬浮窗功能，需要您授权悬浮窗权限。")
                .setPositiveButton("去开启") { _, _ ->
                    FloatWindowManager.init(this)
                }
                .setNegativeButton("取消") { _, _ -> }
                .show()
        }
    }
}
