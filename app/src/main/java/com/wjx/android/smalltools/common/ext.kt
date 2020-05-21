package com.wjx.android.smalltools.common

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.provider.Settings
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/5/21 18:41
 */
fun RecyclerView.itemPadding(padding: Int) {
    addItemDecoration(PaddingItemDecoration(padding, padding, padding, padding))
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