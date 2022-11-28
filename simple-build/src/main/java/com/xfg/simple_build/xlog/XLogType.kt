package com.xfg.simple_build.xlog

import android.util.Log
import androidx.annotation.IntDef

class XLogType {

    @IntDef(V, D, I, W, E, A)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Type{

    }

    companion object{
       const val V:Int = Log.VERBOSE
        const val D = Log.DEBUG
        const val I = Log.INFO
        const val W = Log.WARN
        const val E = Log.ERROR
        const val A = Log.ASSERT
    }
}