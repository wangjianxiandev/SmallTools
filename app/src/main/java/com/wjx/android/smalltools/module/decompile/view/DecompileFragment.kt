package com.wjx.android.smalltools.module.decompile.view

import android.os.Bundle
import com.wjx.android.smalltools.R
import com.wjx.android.smalltools.base.BaseFragment

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/11/25 23:40
 */
class DecompileFragment : BaseFragment() {
    private val type by lazy { arguments?.getInt(TYPE, -1) }
    private val isReverse by lazy { arguments?.getBoolean(REVERSE, false) }

    companion object {
        const val TYPE = "type"
        const val REVERSE = "reverse"
        const val TYPE_SYSTEM = 0
        const val TYPE_THIRD = 1
        const val TYPE_LOCAL = 2
        fun getInstance(type: Int, isReverse: Boolean) = DecompileFragment().apply {
            arguments = Bundle().apply {
                putInt(TYPE, type)
                putBoolean(REVERSE, isReverse)
            }
        }
    }
    override fun getLayoutId() = R.layout.fragment_decompile

    override fun initView() {
    }

    override fun initData() {
    }

}