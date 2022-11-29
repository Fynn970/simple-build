package com.xfg.simple_build.xlog

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simple_build.R

/**
 *@author: md
 *@create: 2022/11/29
 **/
class XViewPrinter(val activity: Activity): XLogPrinter {

    private var rootView: FrameLayout = activity.findViewById<FrameLayout>(android.R.id.content)
    private var recyclerView: RecyclerView = RecyclerView(activity)
    private var adapter :LogAdapter = LogAdapter(android.view.LayoutInflater.from(recyclerView.context))
    private val viewPrinterProvider: XViewPrinterProvider
    init {
        recyclerView.let{
            val lmm = LinearLayoutManager(recyclerView.context)
            it.layoutManager = lmm
            it.adapter = adapter
        }
        viewPrinterProvider  = XViewPrinterProvider(rootView, recyclerView)
    }

    fun getViewProvider(): XViewPrinterProvider{
        return viewPrinterProvider
    }

    override fun print(config: XLogConfig, level: Int, tag: String, printString: String) {
        adapter.addItem(XLogMo(System.currentTimeMillis(), level, tag, printString))
        recyclerView.smoothScrollToPosition(adapter.itemCount - 1)
    }

    companion object{

        private class LogAdapter(val inflater: LayoutInflater) : RecyclerView.Adapter<LogViewHolder>(){
            val logs: MutableList<XLogMo> = mutableListOf()
            fun addItem(xLogMo: XLogMo){
                logs.add(xLogMo)
                notifyItemInserted(logs.size - 1)
            }

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LogViewHolder {
                val view = inflater.inflate(R.layout.xlog_item, parent, false)
                return LogViewHolder(view)
            }

            override fun onBindViewHolder(holder: LogViewHolder, position: Int) {
                val logItem = logs[position]
                val color = getHighlightColor(logItem.level)
                holder.tagView.setTextColor(color)
                holder.messageView.setTextColor(color)

                holder.tagView.text = logItem.getFlattened()
                holder.messageView.text = logItem.log
            }

            private fun getHighlightColor(logLevel:Int): Int {
                var highlight: Int = 0
                highlight = when(logLevel){
                    XLogType.V -> 0xffbbbbbb.toInt()
                    XLogType.D -> 0xffffffff.toInt()
                    XLogType.I -> 0xff6a8759.toInt()
                    XLogType.W -> 0xffbbb529.toInt()
                    XLogType.E -> 0xffff6b68.toInt()
                    else -> 0xffffff00.toInt()
                }
                return highlight
            }

            override fun getItemCount(): Int {
                return logs.size
            }

        }

        private class LogViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            val tagView = itemView.findViewById<TextView>(R.id.tag)
            val messageView = itemView.findViewById<TextView>(R.id.message)
        }
    }

}