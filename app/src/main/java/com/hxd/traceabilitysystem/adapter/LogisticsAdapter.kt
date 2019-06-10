package com.hxd.traceabilitysystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hxd.traceabilitysystem.R
import com.hxd.traceabilitysystem.bean.LogisticsBean
import android.widget.RelativeLayout
import com.hxd.traceabilitysystem.utils.DensityUtil


/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.adapter
 *  文件名:     LogisticsAdapter
 *  创建者:     HXD
 *  创建时间:   2019/6/6 10:00
 *  描述:       TODO
 */

class LogisticsAdapter(private val data: LogisticsBean, private val context: Context) :
    RecyclerView.Adapter<LogisticsAdapter.LogisticsAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogisticsAdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.logistics_recycle_item, parent, false)
        return LogisticsAdapterHolder(view)
    }

    override fun getItemCount(): Int {
        return data.dataList.size
    }

    override fun onBindViewHolder(holder: LogisticsAdapterHolder, position: Int) {
        try {
            holder.tv_status.text = data.dataList[position].context//订单状态
            holder.tv_time.text = data.dataList[position].time//时间

            if (position === 0) {
                //红色的圆点
                holder.iv_status.setImageResource(R.drawable.logistics_shape_circle_red)
                val pointParams =
                    RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 20f), DensityUtil.dp2px(context, 20f))
                pointParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                holder.iv_status.layoutParams = pointParams

                holder.tv_time.setTextColor(context.resources.getColor(R.color.grey))
                holder.tv_status.setTextColor(context.resources.getColor(R.color.grey))

                //灰色的竖线
                val lineParams =
                    RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 1f), ViewGroup.LayoutParams.MATCH_PARENT)
                lineParams.addRule(RelativeLayout.BELOW, R.id.iv_status)//让直线置于圆点下面
                lineParams.addRule(RelativeLayout.CENTER_IN_PARENT)
                holder.iv_line.layoutParams = lineParams

            } else {
                //                holder.iv_status.setBackgroundResource(R.mipmap.ic_logistics_bottom);
                holder.iv_status.setImageResource(com.hxd.traceabilitysystem.R.drawable.logistics_shape_circle_gray)
                val lp = RelativeLayout.LayoutParams(DensityUtil.dp2px(context, 10f), DensityUtil.dp2px(context, 10f))
                lp.addRule(RelativeLayout.CENTER_IN_PARENT)

                holder.iv_status.layoutParams = lp

                holder.tv_time.setTextColor(context.resources.getColor(R.color.red))
                holder.tv_status.setTextColor(context.resources.getColor(R.color.red))

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
    class LogisticsAdapterHolder(view: View) : RecyclerView.ViewHolder(view) {
        var iv_status: ImageView = view.findViewById(R.id.iv_line)
        var tv_status: TextView = view.findViewById(R.id.iv_status)
        var tv_time: TextView = view.findViewById(R.id.tv_status)
        var iv_line: ImageView = view.findViewById(R.id.tv_time)
    }
}