package com.abbaszadeh.sanaz.covid_19.corona.data

import android.util.Log
import com.abbaszadeh.sanaz.covid_19.core.resource.Resource
import com.abbaszadeh.sanaz.covid_19.core.safeApiCall
import com.abbaszadeh.sanaz.covid_19.core.services.RetrofitInstance
import com.abbaszadeh.sanaz.covid_19.corona.data.api.CoronaApi
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem

class CoronaRemoteDataSource {

    val api = RetrofitInstance.getRetrifitInstance().create(CoronaApi::class.java)

    suspend fun getAllCountries(): Resource<ArrayList<CoronaNetworkItem>> {
        var result = safeApiCall {
            api.getAllCountries()
        }
        Log.i("sanazzz", result.data?.size.toString())
        return result
    }
}