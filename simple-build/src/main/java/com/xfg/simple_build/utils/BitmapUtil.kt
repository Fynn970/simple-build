package com.xfg.simple_build.utils

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class BitmapUtil {
    fun saveToFile(bitmap: Bitmap?, file: File?):Boolean {
        if (bitmap == null) return false
        if (file == null) return false
        if(file.exists()){
            file.delete()
        }
        if(file.parentFile?.exists() != true){
            file.parentFile?.mkdirs()
        }
        var fos: FileOutputStream? = null
        try {
            fos = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos)
            fos.flush()
            return true
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
        return false
    }
}