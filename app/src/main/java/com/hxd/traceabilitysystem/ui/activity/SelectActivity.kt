package com.hxd.traceabilitysystem.ui.activity

import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.base.common.MyActivity
import kotlinx.android.synthetic.main.activity_select.*
import org.jetbrains.anko.startActivity

class SelectActivity : MyActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_select
    }

    override fun getTitleId(): Int {
        return 0
    }

    override fun initView() {
        iv_sourceBtn_select.setOnClickListener {
            startActivity<SourceActivity>()
        }
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
