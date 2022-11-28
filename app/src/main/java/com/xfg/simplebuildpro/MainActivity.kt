package com.xfg.simplebuildpro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simplebuildpro.R
import com.fynn.myapplication.MainAdapter
import com.xfg.simple_build.tab.common.IXTabLayout
import com.xfg.simple_build.tab.top.XTabTopInfo
import com.xfg.simple_build.tab.top.XTabTopLayout
import com.xfg.simple_build.xlog.XLog
import com.xfg.simple_build.xlog.XLogConfig
import com.xfg.simple_build.xlog.XLogType
import com.xfg.simplebuildpro.banner.BannerDemoActivity
import com.xfg.simplebuildpro.log.LogDemoActivity
import com.xfg.simplebuildpro.refresh.RefreshDemoActivity

class MainActivity : AppCompatActivity() {

    lateinit var recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler = findViewById(R.id.recycle)

        val list = mutableListOf<String>(
            "刷新",
            "Banner",
            "Log显示"
        )

        recycler.let {
            it.layoutManager = GridLayoutManager(this, 2)
            val adapter = MainAdapter(list)
            it.adapter = adapter
            
            adapter.setOnItemClickListener(object : MainAdapter.OnItemClickListener {
                override fun onItemClickListener(str: String, position: Int) {
                    when(position){
                        0-> {
                            startActivity(Intent(this@MainActivity, RefreshDemoActivity::class.java))
                        }
                        1-> startActivity(Intent(this@MainActivity, BannerDemoActivity::class.java))
                        2-> startActivity(Intent(this@MainActivity, LogDemoActivity::class.java))
                    }
                }

            })
        }
        
//        Toast.makeText(this, "测i大家", Toast.LENGTH_SHORT).show()
//
//        findViewById<Button>(R.id.refresh_overview).setOnClickListener {
//            startActivity(Intent(this, RefreshDemoActivity::class.java))
//
////            XLog.log(object : XLogConfig(){
////                override fun includeThread(): Boolean {
////                    return true
////                }
////
////                override fun stackTraceDepth(): Int {
////                    return 0
////                }
////            }, XLogType.E, "fqhdqwid", "qweqwe")
////            XLog.a("asd")
//
//        }
//        findViewById<Button>(R.id.xbannerdemo).setOnClickListener {
//
//            startActivity(Intent(this, BannerDemoActivity::class.java))
//
//        }
    }

//    override fun onClick(v: View?) {
//        Toast.makeText(this, "测i大家", Toast.LENGTH_SHORT).show()
//
//        when(v!!.id){
//            R.id.xbannerdemo -> {
//            }
//            }
//    }
}