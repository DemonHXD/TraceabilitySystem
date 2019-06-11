package com.hxd.traceabilitysystem.ui.activity

import com.hxd.tabactivityfragment.util.GsonToBean
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.adapter.CardSourceItem.CardPagerAdapter
import com.hxd.traceabilitysystem.adapter.CardSourceItem.ShadowTransformer
import com.hxd.traceabilitysystem.base.common.MyActivity
import com.hxd.traceabilitysystem.bean.ProductionProcessBean
import com.hxd.traceabilitysystem.bean.ProjectBean
import com.hxd.traceabilitysystem.utils.L
import com.hxd.traceabilitysystem.utils.ProjectUrl
import com.kymjs.rxvolley.RxVolley
import com.kymjs.rxvolley.client.HttpCallback
import kotlinx.android.synthetic.main.activity_source.*
import org.jetbrains.anko.startActivity

class SourceActivity : MyActivity() {

    private var mCardShadowTransformer: ShadowTransformer? = null
    private var mCardAdapter: CardPagerAdapter? = null

    override fun getLayoutId(): Int {
        return R.layout.activity_source
    }

    override fun getTitleId(): Int {
        return 0
    }

    override fun initView() {
        rl_detailed_information.setOnClickListener {
            startActivity<DetailedInformationActivity>()
        }
    }

    override fun initData() {
        loadData()
    }

    private fun loadData(){
        mCardAdapter = CardPagerAdapter()
        waitDialog
        RxVolley.get(ProjectUrl.projectUrl + ProjectUrl.productAddress, object : HttpCallback() {
            override fun onSuccess(result: String?) {
                waitDialog.dismiss()
                successDialog
                val projectBean = GsonToBean.JsonToBean<ProjectBean>(result)
                L.i("${projectBean.result.supplierInfo.value}")
                val data = GsonToBean.JsonToBean<ProductionProcessBean>(projectBean.result.supplierInfo.value)
                data.info.forEach { cardItem->
                    mCardAdapter!!.addCardItem(cardItem)
                }
                mCardShadowTransformer = ShadowTransformer(mViewPager, mCardAdapter)
                mCardShadowTransformer!!.enableScaling(true)
                mViewPager.adapter = mCardAdapter
                mViewPager.setPageTransformer(false, mCardShadowTransformer)
                mViewPager.offscreenPageLimit = 3
                mViewPager.currentItem = 0
//                L.i("数据获取成功 $data")
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
