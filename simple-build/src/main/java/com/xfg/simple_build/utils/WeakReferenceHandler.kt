package com.xfg.simple_build.utils

import android.app.Activity
import android.os.Handler
import android.os.Looper
import android.os.Message
import androidx.fragment.app.FragmentActivity
import java.lang.ref.WeakReference

class WeakReferenceHandler<T : FragmentActivity>(
    private val activity: T,
    looper: Looper = Looper.getMainLooper(),
    val handleMsg: (msg: Message) -> Unit
) : Handler(looper) {
    private var weakActivity: WeakReference<T>? = null
    init {
        weakActivity = WeakReference(activity)
    }

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        handleMsg(msg)
    }

    fun removeCallBackEvent(){
        removeCallbacksAndMessages(null)
    }

}