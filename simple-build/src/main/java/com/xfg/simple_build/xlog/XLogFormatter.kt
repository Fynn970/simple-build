package com.xfg.simple_build.xlog

interface XLogFormatter<T> {
    fun format(data: T): String
}