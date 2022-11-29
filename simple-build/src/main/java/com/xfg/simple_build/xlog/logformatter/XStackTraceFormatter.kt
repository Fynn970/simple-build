package com.xfg.simple_build.xlog.logformatter

import java.lang.StringBuilder

class XStackTraceFormatter: XLogFormatter<Array<StackTraceElement?>> {

    override fun format(data: Array<StackTraceElement?>): String {
        val stringBuilder = StringBuilder(128)
        if (data.isEmpty()) {
            return "null"
        }else if (data.size == 1){
            return "\t - ${data[0]}"
        }else{
            data.forEachIndexed(){index, it ->
                if (index == 0){
                    stringBuilder.append("stackTrace: \n")
                }
                if (index != data.size - 1){
                    stringBuilder.append("\t├")
                    stringBuilder.append("$it")
                    stringBuilder.append("\n")
                }else{
                    stringBuilder.append("\t└")
                    stringBuilder.append("$it")
                }
            }
        }
        return stringBuilder.toString()
    }
}