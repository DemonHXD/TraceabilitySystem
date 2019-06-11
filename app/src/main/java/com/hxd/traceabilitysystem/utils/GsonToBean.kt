package com.hxd.tabactivityfragment.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


/**
 *  项目名：    FoodOrigins
 *  包名：      com.hxd.tabactivityfragment.util
 *  文件名:     GsonToBean
 *  创建者:     HXD
 *  创建时间:   2019/3/18 19:52
 *  描述:       json数据转换bean
 */

class GsonToBean {
    companion object {
        val gson: Gson by lazy { Gson() }
        /**
         * 查询信息的json解析
         */
        inline fun <reified RESPONSE> JsonToBean(result: String?): RESPONSE {
            return gson.fromJson(result, RESPONSE::class.java)
        }


        inline fun <reified RESPONSE> JsonToBeanList(result: String?): ArrayList<RESPONSE> {
            val listType = object : TypeToken<List<RESPONSE>>() {}.type
            return gson.fromJson(result, listType)
        }

    }
}