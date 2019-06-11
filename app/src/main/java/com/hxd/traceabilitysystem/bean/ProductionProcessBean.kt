package com.hxd.traceabilitysystem.bean

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.bean
 *  文件名:     ProductionProcessBean
 *  创建者:     HXD
 *  创建时间:   2019/6/11 20:13
 *  描述:       生产流程实体类
 */

data class ProductionProcessBean(val info: ArrayList<ProductionProcess>) {
    data class ProductionProcess(
        val name: String = "",
        val image: String = "",
        val place: String = "",
        val productionTime: String = "",
        val function: String = ""
    )
}