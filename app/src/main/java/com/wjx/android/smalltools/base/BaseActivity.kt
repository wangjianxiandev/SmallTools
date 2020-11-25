package com.wjx.android.smalltools.base

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wjx.android.smalltools.keeplive.Service1

abstract class BaseActivity : AppCompatActivity() {
    abstract fun getLayoutId() : Int
    abstract fun initView()
    abstract fun initData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initKeepLive()
        initView()
        initData()
    }

    private fun initKeepLive() {
        startService(Intent(this, Service1::class.java))
    }
}
