package com.wjx.android.smalltools.module.currentactivity

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.view.accessibility.AccessibilityEvent
import com.wjx.android.smalltools.bean.HistoryData

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/5/15 19:48
 */
class BoxAccessibilityService : AccessibilityService() {

    companion object {
        var instance: BoxAccessibilityService? = null
    }

//    private var showWindow by Preference(Preference.SHOW_WINDOW, false)

    override fun onInterrupt() {

    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (event.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            FloatWindowManager.addItem(
                HistoryData(
                    event.packageName?.toString(),
                    event.className?.toString()
                )
            )
        }
    }

    override fun onServiceConnected() {
        instance = this
        super.onServiceConnected()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        instance = null
        return super.onUnbind(intent)
    }
}