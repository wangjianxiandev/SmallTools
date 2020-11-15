package com.wjx.android.smalltools.common

import android.Manifest.permission
import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.os.Build
import android.os.Environment
import android.telephony.TelephonyManager
import android.text.format.Formatter
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresPermission
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*

/**
 * Created with Android Studio.
 * Description:
 * @author: Wangjianxian
 * @CreateDate: 2020/11/15 23:57
 */
object DeviceUtils {
    fun getBrand(): String {
        return Build.BRAND
    }

    fun getDisplay(): String {
        return Build.DISPLAY
    }

    fun getProduct(): String {
        return Build.PRODUCT
    }

    fun getDevice(): String {
        return Build.DEVICE
    }

    fun getBoard(): String {
        return Build.BOARD
    }

    fun getModel(): String {
        return Build.MODEL
    }

    fun getManufacturer(): String {
        return Build.MANUFACTURER
    }

    fun getBootloader(): String {
        return Build.BOOTLOADER
    }

    fun getHardware(): String {
        return Build.HARDWARE
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    fun getSupportedAbis(): Array<String> {
        return Build.SUPPORTED_ABIS
    }

    fun getRadioVersion(): String {
        return Build.getRadioVersion()
    }

    fun getSdkVersion(): Int {
        return Build.VERSION.SDK_INT
    }

    fun getVersion(): String {
        return Build.VERSION.RELEASE
    }

    fun getKernelVersion(): String {
        val commandResult: ShellUtils.CommandResult =
            ShellUtils.execCommand("cat /proc/version", false)
        return if (commandResult.result === 0) commandResult.successResult else "unknown"
    }

    fun getSDK(): Int {
        return Build.VERSION.SDK_INT
    }

    @SuppressLint("MissingPermission", "HardwareIds")
    @RequiresPermission(permission.READ_PHONE_STATE)
    fun getIMEI(context: Context): String {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                ?: return ""
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) telephonyManager.imei else telephonyManager.deviceId
    }

    @SuppressLint("HardwareIds", "MissingPermission")
    @RequiresPermission(permission.READ_PHONE_STATE)
    fun getMEID(context: Context): String {
        val telephonyManager =
            context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                ?: return ""
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) telephonyManager.meid else telephonyManager.deviceId
    }

    @SuppressLint("MissingPermission")
    @RequiresPermission(permission.READ_PHONE_STATE)
    fun getSN(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Build.getSerial() else Build.SERIAL
    }

    @Throws(SocketException::class)
    fun getMacAddress(): String {
        val networkInterfaces =
            NetworkInterface.getNetworkInterfaces()
        while (networkInterfaces.hasMoreElements()) {
            val networkInterface = networkInterfaces.nextElement()
            if (!networkInterface.name.equals("wlan0", ignoreCase = true)) continue
            val addresses = networkInterface.hardwareAddress
            if (addresses == null || addresses.size == 0) return ""
            val builder = StringBuilder()
            for (b in addresses) {
                builder.append(String.format(Locale.getDefault(), "%02x:", b))
            }
            return builder.deleteCharAt(builder.length - 1).toString()
        }
        return ""
    }

    fun getDeviceWidth(context: Context): Int {
        return context.resources.displayMetrics.widthPixels
    }

    fun getDeviceHeight(context: Context): Int {
        return context.resources.displayMetrics.heightPixels
    }

    fun getRamInfo(context: Context): String {
        val totalSize: Long
        val availableSize: Long
        val activityManager =
            context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val memoryInfo = ActivityManager.MemoryInfo()
        activityManager.getMemoryInfo(memoryInfo)
        totalSize = memoryInfo.totalMem
        availableSize = memoryInfo.availMem
        return String.format(
            "%s / %s",
            Formatter.formatFileSize(context, availableSize),
            Formatter.formatFileSize(context, totalSize)
        )
    }

    fun getRomInfo(context: Context?): String {
        val totalSize =
            Environment.getExternalStorageDirectory().totalSpace
        val availableSize =
            Environment.getExternalStorageDirectory().freeSpace
        return String.format(
            "%s / %s",
            Formatter.formatFileSize(context, availableSize),
            Formatter.formatFileSize(context, totalSize)
        )
    }
}