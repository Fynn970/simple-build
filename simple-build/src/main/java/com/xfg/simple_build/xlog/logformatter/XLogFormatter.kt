package com.xfg.simple_build.xlog.logformatter

interface XLogFormatter<T> {
    fun format(data: T): String
}