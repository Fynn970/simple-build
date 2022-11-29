package com.xfg.simple_build.xlog.printer

import android.graphics.Color
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xfg.simple_build.utils.DisplayUtil

/**
 *@author: md
 *@create: 2022/11/29
 **/
class XViewPrinterProvider(
   private val rootView: FrameLayout,
   private val recycler: RecyclerView
) {
    private var floatingView:View? = null
    private var isOpen = false
    private var logView:FrameLayout? = null
    companion object{
        private val TAG_FLOATING_VIEW = "TAG_FLOATING_VIEW"
        private val TAG_LOG_VIEW = "TAG_LOG_VIEW"
    }

    fun showFloatingView(){
        if (rootView.findViewWithTag<View>(TAG_FLOATING_VIEW) != null){
            return
        }
        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        params.let {
            it.gravity = Gravity.BOTTOM or Gravity.RIGHT
            it.bottomMargin = DisplayUtil.dp2px(100f, recycler.resources)
        }
        val floatingView= getFloatView()
        floatingView?.let {
            it.tag = TAG_FLOATING_VIEW
            it.setBackgroundColor(Color.BLACK)
            it.alpha = 0.8f
        }
        rootView.addView(floatingView, params)

    }

    private fun getFloatView(): View? {
        if (floatingView != null){
            return floatingView
        }
        val textView = TextView(rootView.context)
        textView.setOnClickListener {
            if (!isOpen){
                showLogView()
            }
        }
        textView.text = "XLog"
        floatingView = textView
        return floatingView
    }

    private fun showLogView() {
        if (rootView.findViewWithTag<View>(TAG_LOG_VIEW) != null){
            return
        }
        val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DisplayUtil.dp2px(160f, rootView.resources))
        params.gravity = Gravity.BOTTOM
        val logView = genLogView()
        logView?.let {
            it.tag = TAG_LOG_VIEW
        }
        rootView.addView(logView, params)
    }

    fun closeFloatingView(){
        rootView.removeView(getFloatView())
    }

    private fun genLogView(): View? {
        if (logView != null){
            return logView
        }
        val view = FrameLayout(rootView.context)
        view.let {
            it.setBackgroundColor(Color.BLACK)
            it.addView(recycler)
            val params = FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.gravity = Gravity.END
            val closeView = TextView(rootView.context)
            closeView.setOnClickListener {
                closeLogView()
            }
            closeView.text = "关闭"
            it.addView(closeView, params)
        }
        logView = view
        return logView

    }

    private fun closeLogView() {
        isOpen = false
        rootView.removeView(logView)
    }


}