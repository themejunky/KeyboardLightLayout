package com.kplayout2019

import android.app.Application
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents


class MyMainApplication: Application() {

    lateinit var MGAE :Module_GoogleAnalyticsEvents
    override fun onCreate() {
        super.onCreate()

    }
}