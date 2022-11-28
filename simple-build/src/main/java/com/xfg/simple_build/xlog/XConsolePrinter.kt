package com.xfg.simple_build.xlog

import android.util.Log
import com.xfg.simple_build.xlog.XLogConfig.Companion.MAX_LEN

class XConsolePrinter: XLogPrinter {
    override fun print(config: XLogConfig, level: Int, tag: String, printString: String) {
        val len = printString.length
        val rows:Int = len / MAX_LEN
        if (rows > 0){
            var index = 0
            for (i in 0 until rows){
                Log.println(level, tag, printString.substring(index, index+ MAX_LEN))
                index += MAX_LEN
            }
            if (index != len){
                Log.println(level, tag, printString.substring(index, len))
            }
        }else{
            Log.println(level, tag, printString)
        }
    }
}