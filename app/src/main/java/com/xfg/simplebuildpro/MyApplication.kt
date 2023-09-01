package com.xfg.simplebuildpro

import android.app.Application
import com.example.simple_build.BuildConfig
import com.google.gson.Gson
import com.xfg.simple_build.utils.PreferenceUtil
import com.xfg.simple_build.xlog.printer.XConsolePrinter
import com.xfg.simple_build.xlog.XLogConfig
import com.xfg.simple_build.xlog.XLogManager

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        PreferenceUtil.getPreference(applicationContext)
        XLogManager.init(object : XLogConfig() {
            override fun injectJsonParse(): JsonParser {
                return object : JsonParser {
                    override fun toJson(src: Any): String {
                        return Gson().toJson(src)
                    }
                }
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
            override fun getGlobalTag(): String {
                return "build"
            }

            override fun enable(): Boolean {
                if (BuildConfig.DEBUG){
                    return true
                }
                return false
            }
        }, XConsolePrinter())
    }

}