package com.hxd.traceabilitysystem.bean

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.bean
 *  文件名:     CommonBean
 *  创建者:     HXD
 *  创建时间:   2019/6/11 20:35
 *  描述:       通用信息类
 */

data class CommonBean(val error_code: Int, val result: Common) {
    data class Common(val info: CommonInfo) {
        data class CommonInfo(
            val key: String,
            val value: String,
            val version: Int
        )
    }
}