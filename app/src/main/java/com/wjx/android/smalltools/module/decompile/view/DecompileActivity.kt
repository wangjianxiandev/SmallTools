package com.wjx.android.smalltools.module.decompile.view

import com.wjx.android.smalltools.R
import com.wjx.android.smalltools.base.BaseActivity

class DecompileActivity : BaseActivity() {
    companion object {
        val REVERSE = "reverse"
    }

    private var selectedPosition = 0
    private val titles = arrayListOf("三方应用", "系统应用", "本地安装包")
    private val isReverse by lazy { intent.getBooleanExtra(REVERSE, false) }
    private val fragments = ArrayList<DecompileFragment>()
    private val systemAppFragment by lazy {
        DecompileFragment.getInstance(
            DecompileFragment.TYPE_SYSTEM,
            isReverse
        )
    }
    private val thirdAppFragment by lazy {
        DecompileFragment.getInstance(
            DecompileFragment.TYPE_THIRD,
            isReverse
        )
    }
    private val localAppFragment by lazy {
        DecompileFragment.getInstance(
            DecompileFragment.TYPE_LOCAL,
            isReverse
        )
    }

    override fun getLayoutId() = R.layout.activity_decompile

    override fun initView() {
    }

    override fun initData() {
    }
}