package com.wjx.android.smalltools.module.activity

import android.Manifest
import android.annotation.SuppressLint
import android.os.Build
import com.wjx.android.smalltools.R
import com.wjx.android.smalltools.common.DeviceUtils
import com.wjx.android.smalltools.common.ext.request
import kotlinx.android.synthetic.main.activity_device_info.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class DeviceInfoActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.activity_device_info

    override fun initView() {
        checkPermission()
        initToolbar()

    }

    override fun initData() {
        addDeviceInfo()
    }

    private fun addDeviceInfo() {
        brand.text = DeviceUtils.getBrand()
        display.text = DeviceUtils.getDisplay()
        product.text = DeviceUtils.getProduct()
        device.text = DeviceUtils.getDevice()
        board.text = DeviceUtils.getBoard()
        model.text = DeviceUtils.getModel()
        manufacturer.text = DeviceUtils.getManufacturer()
        bootloader.text = DeviceUtils.getBootloader()
        hardware.text = DeviceUtils.getHardware()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            abis.text = DeviceUtils.getSupportedAbis()!!.contentToString()
        }
        radioVersion.text = Build.getRadioVersion()
        api.text = DeviceUtils.getSDK().toString()
        version.text = DeviceUtils.getVersion()
        os.text = DeviceUtils.getKernelVersion()
        macAddress.text = DeviceUtils.getMacAddress()

        screen.text = String.format(
            "%s * %s",
            DeviceUtils.getDeviceWidth(this),
            DeviceUtils.getDeviceHeight(this)
        )
        ram.text = DeviceUtils.getRamInfo(this)
        rom.text = DeviceUtils.getRomInfo(this)
    }

    private fun initToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        toolbar.title = getString(R.string.device_info)
    }

    @SuppressLint("MissingPermission")
    private fun checkPermission() {
        request(Manifest.permission.READ_PHONE_STATE) {
            onGranted {
//                imei.text = DeviceUtils.getIMEI(this@DeviceInfoActivity)
//                meid.text = DeviceUtils.getMEID(this@DeviceInfoActivity)
//                sn.text = DeviceUtils.getSN()
            }
        }
    }
}