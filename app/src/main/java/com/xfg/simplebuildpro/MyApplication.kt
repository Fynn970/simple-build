package com.xfg.simplebuildpro

import android.app.Application
import com.google.gson.Gson
import com.xfg.simple_build.xlog.XConsolePrinter
import com.xfg.simple_build.xlog.XLogConfig
import com.xfg.simple_build.xlog.XLogManager

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        XLogManager.init(object : XLogConfig() {
            override fun injectJsonParse(): JsonParser {
                return object : JsonParser {
                    override fun toJson(src: Any): String {
                        return Gson().toJson(src)
                    }
                }
            }
            override fun getGlobalTag(): String {
                return "Demo"
            }

            override fun enable(): Boolean {
                return true
            }
        }, XConsolePrinter())
    }

}