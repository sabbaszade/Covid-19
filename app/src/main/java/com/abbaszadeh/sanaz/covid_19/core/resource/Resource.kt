package com.abbaszadeh.sanaz.covid_19.core.resource

import org.json.JSONObject

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
</T> */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val error: JSONObject? = null
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> error2(error: JSONObject?): Resource<T> {
            return Resource(status = Status.ERROR, data = null, error = error, message = null)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}