package com.xfg.simple_build.xlog.printer

import androidx.annotation.NonNull
import com.xfg.simple_build.xlog.XLogConfig

interface XLogPrinter {
    fun print(@NonNull config: XLogConfig, level: Int, tag:String, @NonNull printString: String )
}