package com.hxd.traceabilitysystem.bean

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.bean
 *  文件名:     DetectionBean
 *  创建者:     HXD
 *  创建时间:   2019/6/18 22:11
 *  描述:       检测信息实体类
 */

data class DetectionBean(
    val info: Detection,
    val operator: String = ""
) {
    data class Detection(val ingredient: String = "")
}