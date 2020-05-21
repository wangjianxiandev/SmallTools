package com.wjx.android.smalltools.module.activity

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.recyclerview.widget.GridLayoutManager
import com.wjx.android.smalltools.R
import com.wjx.android.smalltools.adapter.MainAdapter
import com.wjx.android.smalltools.module.calllog.CalllogActivity
import com.wjx.android.smalltools.common.MAIN_LIST
import com.wjx.android.smalltools.common.itemPadding
import com.wjx.android.smalltools.common.startActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_content.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity() {
    private val mMainAdapter by lazy { MainAdapter() }
    override fun getLayoutId() =
        R.layout.activity_main

    override fun initView() {
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)
        initNavView()
        initRecycleView()
    }

    private fun initRecycleView() {
        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            itemPadding(8)
            adapter = mMainAdapter
        }
        mMainAdapter.setOnItemClickListener { _, _, position ->
            when (mMainAdapter.data[position].name) {
                "反编译" -> startActivity<CalllogActivity>(this)
                "当前Activity" -> startActivity<CurrentActivity>(this)
                "本机信息" -> startActivity<CalllogActivity>(this)
            }
        }
    }

    override fun initData() {
        mMainAdapter.setNewInstance(MAIN_LIST)
    }

    private fun initNavView() {
        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.app_name,
            R.string.app_name
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        navigation_draw.setNavigationItemSelectedListener {
            true
        }
    }


}
