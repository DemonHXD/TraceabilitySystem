package com.hxd.traceabilitysystem.ui.activity

import com.hxd.tabactivityfragment.util.GsonToBean
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.base.common.MyActivity
import com.hxd.traceabilitysystem.bean.DetectionBean
import com.hxd.traceabilitysystem.bean.ProjectBean
import com.hxd.traceabilitysystem.utils.L
import com.hxd.traceabilitysystem.utils.ProjectUrl
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
import kotlinx.android.synthetic.main.activity_mechanism.*

class MechanismActivity : MyActivity() {
    override fun getLayoutId(): Int {
        return R.layout.activity_mechanism
    }

    override fun getTitleId(): Int {
        return R.id.tb_title_machanism
    }

    override fun initView() {

    }

    override fun initData() {
        loadData()
    }

    private fun loadData(){
        waitDialog
        RxVolley.get(ProjectUrl.projectUrl + ProjectUrl.productAddress, object : HttpCallback() {
            override fun onSuccess(result: String?) {
                waitDialog.dismiss()
                successDialog
                val projectBean = GsonToBean.JsonToBean<ProjectBean>(result)
                val data = GsonToBean.JsonToBean<DetectionBean>(projectBean.result.detectionInfo.value)
                tv_detection_mechanism.text = data.info.ingredient
//                L.i("数据获取成功 $data")
            }

            override fun onFailure(errorNo: Int, strMsg: String?) {
                waitDialog.dismiss()
                errorDialog
                L.i("数据获取错误 $errorNo, msg:$strMsg")
            }
        })
    }


}
