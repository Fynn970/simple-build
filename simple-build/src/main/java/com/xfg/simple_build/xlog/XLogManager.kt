package com.xfg.simple_build.xlog

import com.xfg.simple_build.xlog.printer.XLogPrinter

class XLogManager private constructor(
    val xLogConfig: XLogConfig,
    xLogPrinters: Array<out XLogPrinter>
) {

    val printers = mutableListOf<XLogPrinter>()

    init {
        printers.addAll(xLogPrinters)
    }

    companion object {
        private var instance: XLogManager? = null

        fun getInstance(): XLogManager? {
            return instance
        }

        fun init(xLogConfig: XLogConfig, vararg xLogPrinter: XLogPrinter) {
            instance = XLogManager(xLogConfig, xLogPrinter)
        }
    }

    fun getConfig(): XLogConfig {
        return xLogConfig
    }

    fun addPrinter(xLogPrinter: XLogPrinter){
        printers.add(xLogPrinter)
    }

    fun removePrinter(xLogPrinter: XLogPrinter){
        printers.remove(xLogPrinter)
    }


}