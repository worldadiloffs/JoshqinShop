package com.example.joshqinshop.util

import android.content.Context


class SharedP private constructor(context: Context) {
    val shared = context.getSharedPreferences("data", 0)
    val edit = shared.edit()


    companion object {
        private var instance: SharedP? = null
        fun getInstance(context: Context): SharedP {
            if (instance == null) {
                instance = SharedP(context)
            }
            return instance!!
        }
    }

    fun setBoolean(isState: Boolean) {
        edit.putBoolean("isState", isState).apply()
    }

    fun getBoolean(): Boolean {
        return shared.getBoolean("isState", false)
    }
}