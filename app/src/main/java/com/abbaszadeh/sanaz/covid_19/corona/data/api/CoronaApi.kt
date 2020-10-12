package com.abbaszadeh.sanaz.covid_19.corona.data.api

import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetwork
import retrofit2.http.GET

interface CoronaApi {

    @GET("countries")
    suspend fun getAllCountries(): CoronaNetwork
}