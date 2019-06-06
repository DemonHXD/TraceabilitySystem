package com.hxd.traceabilitysystem.ui.activity

import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.base.common.MyActivity

class SelectActivity : MyActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_select
    }

    override fun getTitleId(): Int {
        return 0
    }

    override fun initView() {

    }

    override fun initData() {

    }

    /**
     * 状态栏显示为白色
     */
    override fun statusBarDarkFont(): Boolean {
        return !super.statusBarDarkFont()
    }

}
