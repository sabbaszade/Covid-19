package com.abbaszadeh.sanaz.covid_19.core.public

import android.content.Context
import android.net.ConnectivityManager
import com.abbaszadeh.sanaz.covid_19.core.MyApplication

object PublicFunc {
    fun verifyAvailableNetwork(): Boolean {
        val connectivityManager =
            MyApplication.app.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}