package com.abbaszadeh.sanaz.covid_19.core

import com.abbaszadeh.sanaz.covid_19.core.resource.Resource

suspend inline fun <T> safeApiCall(responseFunction: suspend () -> T): Resource<T> {
    return try {
        Resource.success(responseFunction.invoke())
    } catch (e: Exception) {
        Resource.error(e.message.orEmpty(), null)
    }
}