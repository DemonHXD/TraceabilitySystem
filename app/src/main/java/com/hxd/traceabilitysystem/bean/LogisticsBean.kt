package com.hxd.traceabilitysystem.bean

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.bean
 *  文件名:     LogisticsBean
 *  创建者:     HXD
 *  创建时间:   2019/6/6 10:13
 *  描述:       物流数据实体类
 */

/**
 * image:物品图片
 * source://承运来源
 * waybill://运单编号
 * telephone://官方电话
 * status://物流状态 boolean
 * dataList：一个json数据集，里面包含物流时间和物流信息
 */
data class LogisticsBean(
    val info: Logistics,
    val operator: String = ""
) {
    data class Logistics(
        val image: String = "",
        val source: String = "",
        val waybill: String = "",
        val telephone: String = "",
        val status: Boolean = false,
        val info: List<listData>
    ) {
        data class listData(
            val time: Long = 0,
            val context: String = ""
        )
    }
}


