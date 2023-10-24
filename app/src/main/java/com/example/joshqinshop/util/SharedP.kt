package com.example.joshqinshop.util

import android.content.Context
import com.example.joshqinshop.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class SharedP private constructor(context: Context) {
    val shared = context.getSharedPreferences("data", 0)
    val edit = shared.edit()
    val gson = Gson()

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

    fun getSelectedCarsList(): MutableList<Product>{
        val data: String = shared.getString("Selected", "")!!
        if (data == ""){
            return mutableListOf()
        }
        val typeToken = object : TypeToken<MutableList<Product>>(){}.type
        return gson.fromJson(data, typeToken)
    }
    fun setSelectedCarsList(mutableList: MutableList<Product>){
        edit.putString("Selected", gson.toJson(mutableList)).apply()
    }
}