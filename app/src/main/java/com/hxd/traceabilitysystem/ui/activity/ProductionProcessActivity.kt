package com.hxd.traceabilitysystem.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hjq.image.ImageLoader
import com.hxd.tabactivityfragment.util.GsonToBean
import com.hxd.tabactivityfragment.util.ThreadUtil
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.adapter.ProcessAdapter
import com.hxd.traceabilitysystem.base.common.MyActivity
import com.hxd.traceabilitysystem.bean.CommonBean
import com.hxd.traceabilitysystem.bean.MaterialCompanyBean
import com.hxd.traceabilitysystem.bean.ProductionProcessBean
import com.hxd.traceabilitysystem.bean.ProjectBean
import com.hxd.traceabilitysystem.utils.L
import com.hxd.traceabilitysystem.utils.ProjectUrl
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
import kotlinx.android.synthetic.main.activity_production_process.*

/**
 * 详细生产流程页面
 */
class ProductionProcessActivity : MyActivity() {

    private var procesAdapter: ProcessAdapter? = null
    private val linearLayoutManager by lazy { LinearLayoutManager(getActivity()) }

    override fun getLayoutId(): Int {
        return R.layout.activity_production_process
    }

    override fun getTitleId(): Int {
        return R.id.tb_title_process
    }

    override fun initView() {

    }

    override fun initData() {
        loadData()
    }

    private fun loadData() {
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        waitDialog
        RxVolley.get(ProjectUrl.projectUrl + ProjectUrl.productAddress, object : HttpCallback() {
            override fun onSuccess(result: String?) {
                ThreadUtil.runOnMainThread(Runnable {
                    val projectBean = GsonToBean.JsonToBean<ProjectBean>(result)
                    val processBean = GsonToBean.JsonToBean<ProductionProcessBean>(projectBean.result.producerInfo.value)
                    RxVolley.get(ProjectUrl.projectUrl + processBean.operator, object : HttpCallback() {
                        override fun onSuccess(t: String?) {
                            waitDialog.dismiss()
                            successDialog
                            val commonBean = GsonToBean.JsonToBean<CommonBean>(t)
                            val company = GsonToBean.JsonToBean<MaterialCompanyBean>(commonBean.result.info.value)
                            ImageLoader.loadCircleImage(iv_image_process, company.image)
                            tv_title_process.text = company.name
                        }

                        override fun onFailure(errorNo: Int, strMsg: String?) {
                            waitDialog.dismiss()
                            errorDialog
                            L.i("数据获取错误 $errorNo, msg:$strMsg")
                        }

                    })
//                    L.i("ProductionProcessActivity $processBean")
                    procesAdapter = ProcessAdapter(processBean)
                    recycle_process.adapter = procesAdapter
                    recycle_process.layoutManager = linearLayoutManager
                    procesAdapter!!.notifyDataSetChanged()
                })
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
