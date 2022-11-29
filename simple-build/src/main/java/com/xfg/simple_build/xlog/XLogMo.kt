package com.xfg.simple_build.xlog

import java.text.SimpleDateFormat
import java.util.*

/**
 *@author: md
 *@create: 2022/11/29
 **/
class XLogMo(var timeMillis:Long = 0,
             var level: Int = 0,
             var tag:String = "",
             var log:String = "" ){



    companion object{
        private val sdf = SimpleDateFormat("yy-mm-dd HH:mm:ss", Locale.CHINA)
    }

    fun getFlattened():String{
        return "${format(timeMillis)} | $level | $tag |:"
    }

    fun flattenedLog():String{
        return "${getFlattened()} \n $log"
    }

    private fun format(timeMillis: Long): String{
        return sdf.format(timeMillis)
    }
}