package com.hxd.traceabilitysystem.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.hxd.traceabilitysystem.R
import android.widget.RelativeLayout
import com.hxd.tabactivityfragment.util.ThreadUtil
import com.hxd.traceabilitysystem.bean.ProductionProcessBean
import com.hxd.traceabilitysystem.utils.DensityUtil
import com.hxd.traceabilitysystem.utils.L
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.adapter
 *  文件名:     LogisticsAdapter
 *  创建者:     HXD
 *  创建时间:   2019/6/6 10:00
 *  描述:       TODO
 */

class ProcessAdapter(private val data: ProductionProcessBean) :
    RecyclerView.Adapter<ProcessAdapter.ProcessAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProcessAdapterHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_process, parent, false)
        return ProcessAdapterHolder(view)
    }

    override fun getItemCount(): Int {
        return data.info.size
    }

    override fun onBindViewHolder(holder: ProcessAdapterHolder, position: Int) {
//        L.i("ProcessAdapter: ${data.info[position]}")
        ThreadUtil.runOnMainThread(Runnable {
            holder.tv_process.text = data.info[position].title
            holder.tv_time.text = stringToDate(data.info[position].timestamp)
            holder.tv_workshop.text = data.info[position].workshop
            holder.tv_principal.text = data.info[position].principal

            if (position == data.info.size - 1) {
                holder.iv_bottomArrow.visibility = View.GONE
            }
        })
    }


    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ProcessAdapterHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tv_time: TextView = view.findViewById(R.id.tv_time_itemProcess)
        val tv_principal: TextView = view.findViewById(R.id.tv_principal_itemProcess)
        val tv_process: TextView = view.findViewById(R.id.tv_process_itemProcess)
        val tv_workshop: TextView = view.findViewById(R.id.tv_workshop_itemProcess)
        val iv_bottomArrow: ImageView = view.findViewById(R.id.iv_bottomArrow_process)
    }

    private fun stringToDate(time: Long): String {
        val date = Date(time)
        val sd = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return sd.format(date)
    }

}