package com.hxd.traceabilitysystem.adapter

import android.content.Context
import android.widget.TextView
import android.support.v7.widget.RecyclerView
import android.widget.RelativeLayout
import android.view.ViewGroup
import com.hxd.traceabilitysystem.utils.DensityUtil
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.bean.LogisticsBean
import com.hxd.traceabilitysystem.utils.ToolsUtil


/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.adapter
 *  文件名:     LogisticsAdapter
 *  创建者:     HXD
 *  创建时间:   2019/6/14 12:40
 *  描述:       TODO
 */

class LogisticsAdapter(private val context: Context, private val bean: List<LogisticsBean.Logistics.listData>) :
    RecyclerView.Adapter<LogisticsAdapter.LogisticsAdapterHolder>() {
    private val mLayoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getItemCount(): Int {
        return bean.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogisticsAdapterHolder {
        return LogisticsAdapterHolder(
            mLayoutInflater.inflate(
                R.layout.item_logistics,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LogisticsAdapterHolder, position: Int) {

        try {
            holder.tv_status.text = bean[position].context//订单状态
            holder.tv_time.text = ToolsUtil.stringToDate(bean[position].time)//时间

            if (position == 0) {
                //红色的圆点
                holder.iv_status.setImageResource(R.drawable.logistics_shape_circle_red)
                val pointParams =
                    RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 20f), DensityUtil.dp2px(context, 20f))
                pointParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                holder.iv_status.layoutParams = pointParams

                holder.tv_time.setTextColor(context.resources.getColor(R.color.red))
                holder.tv_status.setTextColor(context.resources.getColor(R.color.red))

                //灰色的竖线
                val lineParams =
                    RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 1f), ViewGroup.LayoutParams.MATCH_PARENT)
                lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status)//让直线置于圆点下面
                lineParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                holder.iv_line.layoutParams = lineParams

            } else {
                //                holder.iv_status.setBackgroundResource(R.mipmap.ic_logistics_bottom);
                holder.iv_status.setImageResource(R.drawable.shape_circle_logistics_gray)
                val lp = RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 10f), DensityUtil.dp2px(context, 10f))
                lp.addRule(RelativeLayout.CENTER_IN_PARENT)

                holder.iv_status.layoutParams = lp

                holder.tv_time.setTextColor(context.resources.getColor(R.color.black60))
                holder.tv_status.setTextColor(context.resources.getColor(R.color.black60))

                //灰色的竖线
                val lineParams =
                    RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 1f), ViewGroup.LayoutParams.MATCH_PARENT)
                //                lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status);//让直线置于圆点下面
                lineParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                holder.iv_line.layoutParams = lineParams
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class LogisticsAdapterHolder internal constructor(view: View) : RecyclerView.ViewHolder(view) {

        internal var iv_status: ImageView = view.findViewById(R.id.iv_status) as ImageView
        internal var tv_status: TextView = view.findViewById(R.id.tv_status)
        internal var tv_time: TextView = view.findViewById(R.id.tv_time)
        internal var iv_line: ImageView = view.findViewById(R.id.iv_line) as ImageView

    }
}
