package com.xfg.simple_build.xlog

import com.xfg.simple_build.xlog.logformatter.XStackTraceFormatter
import com.xfg.simple_build.xlog.logformatter.XThreadFormatter
import com.xfg.simple_build.xlog.printer.XLogPrinter

abstract class XLogConfig {

    companion object {
        val MAX_LEN = 512

        val X_STRACK_TRACE_FORMATTER = XStackTraceFormatter()
        val X_THREAD_FORMATTER = XThreadFormatter()
    }

    open fun injectJsonParse(): JsonParser?{
        return null
    }

    //是否包含线程信息
    open fun includeThread():Boolean{
        return false
    }

    open fun stackTraceDepth(): Int{
        return 5
    }

    open fun printers():Array<XLogPrinter>?{
        return null
    }

    open fun getGlobalTag(): String {
        return "XTAG"
    }


    open fun enable(): Boolean {
        return true
    }

    interface JsonParser{
        fun toJson(src: Any): String
    }
}