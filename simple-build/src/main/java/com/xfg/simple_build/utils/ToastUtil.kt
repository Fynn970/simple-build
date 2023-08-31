package com.xfg.simple_build.utils

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

object ToastUtil {

    fun showToast(context: Context, str: CharSequence?){
        if (str.isNullOrEmpty()){
            return
        }
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }

    fun showToast(context: Context, @StringRes str: Int){
        Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
    }
}