package com.xfg.simple_build.xlog

import androidx.annotation.NonNull

interface XLogPrinter {
    fun print(@NonNull config: XLogConfig, level: Int, tag:String, @NonNull printString: String )
}