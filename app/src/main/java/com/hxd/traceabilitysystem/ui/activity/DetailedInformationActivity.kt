package com.hxd.traceabilitysystem.ui.activity

import com.hxd.tabactivityfragment.util.GsonToBean
import com.hxd.tabactivityfragment.util.ThreadUtil
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.base.common.MyActivity
import com.hxd.traceabilitysystem.bean.CommonBean
import com.hxd.traceabilitysystem.bean.MaterialCompanyBean
import com.hxd.traceabilitysystem.utils.L
import com.hxd.traceabilitysystem.utils.ProjectUrl
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
import kotlinx.android.synthetic.main.activity_detailed_information.*

/**
 * 原材料公司详细信息页
 */

class DetailedInformationActivity : MyActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_detailed_information
    }

    override fun getTitleId(): Int {
        return R.id.tb_title_DetailedInformationActivity
    }

    override fun initView() {

    }

    override fun initData() {
        loadData()
    }

    private fun loadData(){
        waitDialog
        RxVolley.get(ProjectUrl.projectUrl + ProjectUrl.rawMaterialAddress, object : HttpCallback() {
            override fun onSuccess(result: String?) {
                waitDialog.dismiss()
                successDialog
                val projectBean = GsonToBean.JsonToBean<CommonBean>(result)
                L.i("${projectBean.result.info.value}")
                val data = GsonToBean.JsonToBean<MaterialCompanyBean>(projectBean.result.info.value)
               ThreadUtil.runOnMainThread(Runnable {
                   tv_name_detailedInformation.text = data.name
                   tv_registeredCapital_detailedInformation.text = data.registeredCapital
                   tv_taxpayerRegistration_detailedInformation.text = data.taxpayerRegistration
                   tv_dateOfEstablishment_detailedInformation.text = data.dateOfEstablishment
                   tv_businessRegistrationNumber_detailedInformation.text = data.businessRegistrationNumber
                   tv_registrationAuthority_detailedInformation.text = data.registrationAuthority
                   tv_registeredAddress_detailedInformation.text = data.registeredAddress
                   tv_scope_detailedInformation.text = data.scope
                   tv_dateOfApproval_detailedInformation.text = data.dateOfApproval
               })
                L.i("数据获取成功 $data")
            }

            override fun onFailure(errorNo: Int, strMsg: String?) {
                waitDialog.dismiss()
                errorDialog
                L.i("数据获取错误 $errorNo, msg:$strMsg")
            }
        })
    }

    /**
     * 状态栏显示为白色
     */
    override fun statusBarDarkFont(): Boolean {
        return !super.statusBarDarkFont()
    }



}
