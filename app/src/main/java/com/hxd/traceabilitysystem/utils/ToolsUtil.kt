package com.hxd.traceabilitysystem.utils

import java.text.SimpleDateFormat
import java.util.*

/**
 *  项目名：    TraceabilitySystem
 *  包名：      com.hxd.traceabilitysystem.utils
 *  文件名:     ToolsUtil
 *  创建者:     HXD
 *  创建时间:   2019/6/14 10:16
 *  描述:       TODO
 */

class ToolsUtil {
    companion object{
        fun stringToDate(time: Long): String {
            val date = Date(time)
            val sd = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
            return sd.format(date)
        }
    }
}