package com.gr.assignment

import android.content.Context
import android.content.Context.MODE_PRIVATE

class Prefs(context: Context) {
    private val prefNm = "mPref"
    private val prefs = context.getSharedPreferences(prefNm, MODE_PRIVATE)

    var schoolToken : String?
    get() = prefs.getString("schoolToken",null)
    set(value) {
        prefs.edit().putString("schoolToken",value).apply()
    }

    var userToken : String?
    get() = prefs.getString("userToken",null)
    set(value) {
        prefs.edit().putString("userToken",value).apply()
    }

}