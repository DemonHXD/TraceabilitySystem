package com.hxd.traceabilitysystem.ui.activity

import com.hxd.tabactivityfragment.util.GsonToBean
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.base.common.MyActivity
import com.hxd.traceabilitysystem.bean.LogisticsBean
import com.hxd.traceabilitysystem.bean.ProjectBean
import com.hxd.traceabilitysystem.utils.L
import com.hxd.traceabilitysystem.utils.ProjectUrl
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
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

        iv_manufacturerBtn_select.setOnClickListener {
            startActivity<ProductionProcessActivity>()
        }

        iv_logisticsBtn_select.setOnClickListener {
            startActivity<LogisticsActivity>()
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
