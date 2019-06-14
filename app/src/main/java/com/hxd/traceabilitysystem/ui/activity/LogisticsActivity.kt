package com.hxd.traceabilitysystem.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.base.common.MyActivity
import com.hjq.image.ImageLoader
import com.hxd.tabactivityfragment.util.GsonToBean
import com.hxd.traceabilitysystem.adapter.LogisticsAdapter
import com.hxd.traceabilitysystem.bean.LogisticsBean
import com.hxd.traceabilitysystem.bean.ProjectBean
import com.hxd.traceabilitysystem.utils.L
import com.hxd.traceabilitysystem.utils.ProjectUrl
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
import kotlinx.android.synthetic.main.activity_logistics.*


/**
 * 物流界面
 */

class LogisticsActivity : MyActivity() {

    private var logisticsAdapter:LogisticsAdapter? = null
    private val linearLayoutManager by lazy { LinearLayoutManager(getActivity()) }

    override fun getLayoutId(): Int {
        return R.layout.activity_logistics
    }

    override fun getTitleId(): Int {
        return R.id.tb_title_logistics
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
                val data = GsonToBean.JsonToBean<LogisticsBean>(projectBean.result.logisticsInfo.value)
                ImageLoader.loadImage(iv_image_logistics, data.info.image)
                tv_status_logistics.text = if(data.info.status) "已签收" else "未签收"
                tv_source_logistics.text = data.info.source
                tv_waybill_logistics.text = data.info.waybill
                tv_telephone_logistics.text = data.info.telephone
//                val list = data.info.info.reversed()
                logisticsAdapter = LogisticsAdapter(getActivity(),data.info.info.reversed())
                recycle_logitics.adapter = logisticsAdapter
                recycle_logitics.layoutManager = linearLayoutManager
                L.i("数据获取成功 $data")
            }

            override fun onFailure(errorNo: Int, strMsg: String?) {
                waitDialog.dismiss()
                errorDialog
                L.i("数据获取错误 $errorNo, msg:$strMsg")
            }
        })
    }
}
