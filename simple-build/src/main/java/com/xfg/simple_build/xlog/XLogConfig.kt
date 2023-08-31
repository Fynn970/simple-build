package com.xfg.simple_build.xlog

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

    //设置堆栈深度 默认为5
    open fun stackTraceDepth(): Int{
        return 5
    }

    open fun printers():Array<XLogPrinter>?{
        return null
    }

    //设置全局tag
    open fun getGlobalTag(): String {
        return "XTAG"
    }

    //是否显示tag
    open fun enable(): Boolean {
        return true
    }

    interface JsonParser{
        fun toJson(src: Any): String
    }
}