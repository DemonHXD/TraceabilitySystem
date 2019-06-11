package com.hxd.traceabilitysystem.bean

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.bean
 *  文件名:     ProjectBean
 *  创建者:     HXD
 *  创建时间:   2019/6/11 19:07
 *  描述:       产品实体类
 */

data class ProjectBean(val error_code: Int = 0, val result: ProjectResult) {
    data class ProjectResult(
        val alliance: data,
        val initInfo: data,
        val logisticsInfo: data,
        val producerInfo: data,
        val res: data,
        val supplierInfo: data
    ) {
        data class data(val key:String = "", val value:String = "", val version:Int = 0)
    }
}
