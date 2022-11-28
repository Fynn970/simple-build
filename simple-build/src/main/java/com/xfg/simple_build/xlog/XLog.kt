package com.xfg.simple_build.xlog

import android.util.Log
import androidx.annotation.NonNull

class XLog {

    companion object{

        private val X_LOG_PACKAGE = XLog::class.qualifiedName

        fun v(vararg any: Any){
            log(type = XLogType.V, contents = any)
        }

        fun vt(tag: String, vararg any: Any){
            log(type = XLogType.V,tag = tag, contents = any)
        }

        fun d(vararg any: Any){
            log(type = XLogType.D, contents = any)
        }

        fun dt(tag: String, vararg any: Any){
            log(type = XLogType.D,tag = tag, contents = any)
        }

        fun i(vararg any: Any){
            log(type = XLogType.I, contents = any)
        }

        fun it(tag: String, vararg any: Any){
            log(type = XLogType.I,tag = tag, contents = any)
        }

        fun w(vararg any: Any){
            log(type = XLogType.W, contents = any)
        }

        fun wt(tag: String, vararg any: Any){
            log(type = XLogType.W,tag = tag, contents = any)
        }

        fun e(vararg any: Any){
            log(type = XLogType.E, contents = any)
        }

        fun et(tag: String, vararg any: Any){
            log(type = XLogType.E,tag = tag, contents = any)
        }

        fun a(vararg any: Any){
            log(type = XLogType.A, contents = any)
        }

        fun at(tag: String, vararg any: Any){
            log(type = XLogType.A, tag = tag, contents = any)
        }

        fun log(@NonNull config: XLogConfig = XLogManager.getInstance()!!.xLogConfig, @XLogType.Type type: Int, tag: String = XLogManager.getInstance()!!.getConfig().getGlobalTag(), vararg contents: Any){

            if (!config.enable()){
                return
            }
            val stringBuilder = StringBuilder()
            if (config.includeThread()){
                val threadInfo = XLogConfig.X_THREAD_FORMATTER.format(Thread.currentThread())
                stringBuilder.append(threadInfo).append("\n")
            }
            if (config.stackTraceDepth() > 0){
                val stackTrace = XLogConfig.X_STRACK_TRACE_FORMATTER.format(XStackTraceUtil.getCropReadStackTrace(Throwable().stackTrace, X_LOG_PACKAGE!!, config.stackTraceDepth()))
                stringBuilder.append(stackTrace)
            }
            val body = parseBody(config, *contents, )
            stringBuilder.append(body)
            val printers = (if(config.printers() != null) config.printers()!!.toList() else XLogManager.getInstance()?.printers)
                ?: return
            printers.forEach {
                it.print(config, type, tag, stringBuilder.toString())
            }

        }

       private fun parseBody( config: XLogConfig, vararg  contents:Any):String?{
           if (config.injectJsonParse() != null){
               return config.injectJsonParse()?.toJson(contents)
           }
            val stringBuilder = StringBuilder()
            contents.forEach {
                stringBuilder.append(it).append(";")
            }
            if (stringBuilder.isNotEmpty()){
                stringBuilder.deleteCharAt(stringBuilder.length -1 )
            }
            return stringBuilder.toString()
        }
    }

}