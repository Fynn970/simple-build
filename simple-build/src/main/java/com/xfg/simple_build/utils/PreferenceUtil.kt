package com.xfg.simple_build.utils

import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class PreferenceUtil<T> (private val key: String, private val default:T):ReadWriteProperty<Any?, T> {

    companion object {
        private const val FILENAME = "file_name";
        private lateinit var sharedPreferences: SharedPreferences

        fun getPreference(context: Context){
            sharedPreferences = context.applicationContext.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)
        }

        fun clearData(){
            sharedPreferences.edit().clear().apply()
        }

        fun clearDataByKey(key:String){
            sharedPreferences.edit().remove(key).apply()
        }

    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
       return getPreference(key, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setPreference(key, value)
    }

    fun getPreference(key: String, default: T):T = with(sharedPreferences){
        val date = when(default){
            is Int-> getInt(key, default)
            is String -> getString(key, default)
            is Boolean -> getBoolean(key, default)
            is Long -> getLong(key, default)
            is Float -> getFloat(key, default)
            else -> throw IllegalArgumentException("This type can be get from Preferences")
        }
        return date as T
    }

    fun setPreference(key: String, value: T) = with(sharedPreferences.edit()){
        when(value){
            is Int-> putInt(key, value)
            is String -> putString(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> throw IllegalArgumentException("This type can be get from Preferences")
        }.apply()
    }
}