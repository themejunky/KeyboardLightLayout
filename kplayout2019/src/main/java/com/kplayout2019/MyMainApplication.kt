package com.kplayout2019

import android.app.Application
import android.util.Log
import module.themejunky.com.tj_gae.Module_GoogleAnalyticsEvents
import java.io.IOException
import java.net.HttpURLConnection
import java.net.ProtocolException
import java.net.URL

var packageNameApp=""
var redirectGP = ""
var isKplusOnStore=false

open class MyMainApplication: Application() {


    lateinit var MGAE :Module_GoogleAnalyticsEvents
    override fun onCreate() {
        super.onCreate()
        packageNameApp= packageName
        MGAE= Module_GoogleAnalyticsEvents.getInstance(this,getString(R.string.id_google_analytics))
        val thread = Thread(Runnable {
            try {
                try {
                    isKplusOnStore =  availableOnGooglePlay("com.themejunky.keyboardplus")
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        })

        thread.start()

    }

    @Throws(IOException::class)
    private fun availableOnGooglePlay(packageName: String): Boolean {
        val url = URL("https://play.google.com/store/apps/details?id=$packageName")
        val httpURLConnection = url.openConnection() as HttpURLConnection
        try {
            httpURLConnection.requestMethod = "GET"
        } catch (e: ProtocolException) {
            e.printStackTrace()
        }

        try {
            httpURLConnection.connect()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        val responseCode = httpURLConnection.responseCode
        Log.d("Asfdasdasdf", "responseCode for $packageName: $responseCode")
        // code 200
        // this will be HttpURLConnection.HTTP_NOT_FOUND or code 404 if the package is not found
        return responseCode == HttpURLConnection.HTTP_OK
    }

}