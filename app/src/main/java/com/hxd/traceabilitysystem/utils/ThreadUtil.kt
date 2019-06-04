package com.hxd.tabactivityfragment.util

import android.os.Handler
import android.os.Looper

/*
 *  项目名：    HeimaPlayer
 *  包名：      com.itheima.plaer.util
 *  文件名:     ThreadUtil
 *  创建者:     HXD
 *  创建时间:   2019/2/28 19:48
 *  描述:       TODO
 */

object ThreadUtil {
    val handler = Handler(Looper.getMainLooper())

    /**
     * 运行在主线程中
     */
    fun runOnMainThread(runnable: Runnable){
        handler.post(runnable)
    }

    fun postRunOnMainThread(runnable: Runnable){
        handler.postDelayed(runnable, 1500)
    }
}