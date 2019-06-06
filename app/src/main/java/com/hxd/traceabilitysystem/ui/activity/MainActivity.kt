package com.hxd.traceabilitysystem.ui.activity

import android.app.Activity
import android.content.Intent
import com.hjq.permissions.OnPermission
import com.hjq.permissions.Permission
import com.hjq.permissions.XXPermissions
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.base.common.MyActivity
import com.hxd.traceabilitysystem.utils.L
import com.yzq.zxinglibrary.android.CaptureActivity
import com.yzq.zxinglibrary.bean.ZxingConfig
import com.yzq.zxinglibrary.common.Constant
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MyActivity() {

    private val REQUEST_CODE_SCAN:Int = 1111

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun getTitleId(): Int {
        return 0
    }

    override fun initView() {
        iv_saoBtn_home.setOnClickListener {
            XXPermissions.with(getActivity()).permission(Permission.CAMERA, Permission.READ_EXTERNAL_STORAGE).request(object :
                OnPermission {
                //权限获取成功
                override fun hasPermission(granted: List<String>, isAll: Boolean) {
                    val intent = Intent(getActivity(), CaptureActivity::class.java)
                    val config = ZxingConfig()
                    config.isFullScreenScan = false//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
                    intent.putExtra(Constant.INTENT_ZXING_CONFIG, config)
                    startActivityForResult(intent, REQUEST_CODE_SCAN)
                }
                //权限获取失败
                override fun noPermission(denied: List<String>, quick: Boolean) {
                    L.i("没有权限无法扫描呦")
                }
            })

        }
    }

    override fun initData() {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val content = data.getStringExtra(Constant.CODED_CONTENT)
                L.i(content)
            }
        }
    }

    /**
     * 状态栏显示为白色
     */
    override fun statusBarDarkFont(): Boolean {
        return !super.statusBarDarkFont()
    }


}
