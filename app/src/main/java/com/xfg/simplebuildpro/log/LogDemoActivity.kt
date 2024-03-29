package com.xfg.simplebuildpro.log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.simplebuildpro.R
import com.xfg.simple_build.xlog.XLog
import com.xfg.simple_build.xlog.XLogConfig
import com.xfg.simple_build.xlog.XLogType

class LogDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_demo)

        findViewById<Button>(R.id.logbtn).setOnClickListener {
            printLog()
        }
    }

    private fun printLog(){
        XLog.log(object : XLogConfig() {
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, XLogType.E, "fqhdqwid", "qweqwe")
        XLog.a("asd")
    }
}