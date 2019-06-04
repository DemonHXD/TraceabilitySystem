package com.hxd.traceabilitysystem.base.common

import android.Manifest
import android.content.pm.ActivityInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.view.View
import com.hjq.bar.OnTitleBarListener
import com.hjq.bar.TitleBar
import com.hjq.base.BaseActivity
import com.hjq.base.BaseDialog
import com.hjq.dialog.ToastDialog
import com.hjq.dialog.WaitDialog
import com.hjq.toast.ToastUtils
import com.hxd.traceabilitysystem.handle.ActivityStackManager
import com.hxd.traceabilitysystem.handle.DebugUtils

/**
 * author : Android 轮子哥
 * github : https://github.com/getActivity/AndroidProject
 * time   : 2018/10/18
 * desc   : 项目中的 Activity 基类
 */
abstract class MyActivity : UIActivity(), OnTitleBarListener {
    val waitDialog: BaseDialog by lazy {
        WaitDialog.Builder(this)
            .setMessage("加载中...")
            .show()
    }
    val waitImageDialog: BaseDialog by lazy {
        WaitDialog.Builder(this)
            .setMessage("正在上传图片...")
            .show()
    }
    val successDialog: BaseDialog by lazy {
        ToastDialog.Builder(this)
            .setType(ToastDialog.Type.FINISH)
            .setMessage("成功")
            .show()
    }
    val errorDialog: BaseDialog by lazy {
        ToastDialog.Builder(this)
            .setType(ToastDialog.Type.ERROR)
            .setMessage("失败")
            .show()
    }

    private val titleBar: TitleBar?
        get() = if (titleId > 0 && findViewById<View>(titleId) is TitleBar) {
            findViewById(titleId)
        } else null

    /**
     * 获取当前的 Application 对象
     */
    val myApplication: MyApplication
        get() = application as MyApplication


    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityStackManager.getInstance().onActivityCreated(this)
        super.onCreate(savedInstanceState)
        requestWritePermission()

    }

    //动态获取多媒体权限
    private fun requestWritePermission() {
        if (ActivityCompat.checkSelfPermission(
                getActivity<BaseActivity>(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                getActivity<BaseActivity>(),
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    }

    override fun initLayout() {
        super.initLayout()

        // 初始化标题栏的监听
        if (titleId > 0) {
            if (findViewById<View>(titleId) is TitleBar) {
                (findViewById<View>(titleId) as TitleBar).onTitleBarListener = this
            }
        }

        initOrientation()
    }

    /**
     * 初始化横竖屏方向，会和 LauncherTheme 主题样式有冲突，注意不要同时使用
     */
    protected fun initOrientation() {
        // 当前 Activity 不能是透明的并且没有指定屏幕方向，默认设置为竖屏
        if (requestedOrientation == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
    }

    /**
     * 设置标题栏的标题
     */
    override fun setTitle(titleId: Int) {
        title = getText(titleId)
    }

    /**
     * 设置标题栏的标题
     */
    override fun setTitle(title: CharSequence) {
        super.setTitle(title)
        val titleBar = titleBar
        if (titleBar != null) {
            titleBar.title = title
        }
    }

    override fun statusBarDarkFont(): Boolean {
        //返回true表示黑色字体
        return true
    }

    /**
     * [OnTitleBarListener]
     */

    // TitleBar 左边的View被点击了
    override fun onLeftClick(v: View) {
        onBackPressed()
    }

    // TitleBar 中间的View被点击了
    override fun onTitleClick(v: View) {}

    // TitleBar 右边的View被点击了
    override fun onRightClick(v: View) {}

    override fun onDestroy() {
        super.onDestroy()
        ActivityStackManager.getInstance().onActivityDestroyed(this)
    }

    /**
     * 显示吐司
     */
    fun toast(s: CharSequence) {
        ToastUtils.show(s)
    }

    fun toast(@StringRes id: Int) {
        // https://github.com/getActivity/ToastUtils/issues/31
        ToastUtils.show(getString(id))
    }

    fun toast(`object`: Any) {
        ToastUtils.show(`object`)
    }

    /**
     * 打印日志
     */
    fun log(`object`: Any?) {
        if (DebugUtils.isDebug(this)) {
            Log.v(javaClass.simpleName, `object`?.toString() ?: "null")
        }
    }
}