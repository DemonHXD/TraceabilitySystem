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
 * image:物流显示的一个图片
 * title：物流显示的一个标题
 *
 * dataList：一个json数据集，里面包含物流时间和物流信息
 */
data class LogisticsBean(val image: String, val title: String, val dataList: ArrayList<list>) {
    data class list(val time: String, val context: String)
}
