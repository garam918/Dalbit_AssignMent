package com.gr.assignment

import android.app.Application

class SingleTon : Application() {
    companion object{
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        prefs = Prefs(applicationContext)
        super.onCreate()
    }
}