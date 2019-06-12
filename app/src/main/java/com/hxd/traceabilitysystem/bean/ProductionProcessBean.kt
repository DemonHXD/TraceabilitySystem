package com.hxd.traceabilitysystem.bean

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.bean
 *  文件名:     ProductionProcessBean
 *  创建者:     HXD
 *  创建时间:   2019/6/12 14:45
 *  描述:       生产流程实体类
 *  principal:责任人
 *  timestamp：操作时间
 *  title：流程名
 *  workshop：责任车间
 */

data class ProductionProcessBean(val info: ArrayList<ProductionProcess>, val operator: String) {
    data class ProductionProcess(
        val principal: String = "",
        val timestamp: Long = 1560328779354,
        val title: String = "",
        val workshop: String = ""
    )
}