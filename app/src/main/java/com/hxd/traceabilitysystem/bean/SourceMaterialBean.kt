package com.hxd.traceabilitysystem.bean

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.bean
 *  文件名:     SourceMaterialBean
 *  创建者:     HXD
 *  创建时间:   2019/6/11 20:13
 *  描述:       原材料实体类
 */

data class SourceMaterialBean(val info: ArrayList<SourceMaterial>) {
    data class SourceMaterial(
        val name: String = "",
        val image: String = "",
        val place: String = "",
        val productionTime: String = "",
        val function: String = ""
    )
}