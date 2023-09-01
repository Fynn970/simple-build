package com.xfg.simplebuildpro.log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.simplebuildpro.R
import com.xfg.simple_build.utils.PreferenceUtil
import com.xfg.simple_build.xlog.*
import com.xfg.simple_build.xlog.printer.XViewPrinter

class LogDemoActivity : AppCompatActivity() {

    private lateinit var viewPrinter : XViewPrinter

    private var count:Int by PreferenceUtil("count", 0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_demo)

        findViewById<Button>(R.id.logbtn).setOnClickListener {
            printLog()
        }
        viewPrinter = XViewPrinter(this)
        viewPrinter.getViewProvider().showFloatingView()
        XLogManager.getInstance()?.addPrinter(viewPrinter)
    }

    private fun printLog(){
        count += 1
        XLog.log(object : XLogConfig() {
            override fun includeThread(): Boolean {
                return true
            }

            override fun stackTraceDepth(): Int {
                return 0
            }
        }, XLogType.E, "fqhdqwid", "qweqwe $count")
        XLog.a("asd")
    }
}