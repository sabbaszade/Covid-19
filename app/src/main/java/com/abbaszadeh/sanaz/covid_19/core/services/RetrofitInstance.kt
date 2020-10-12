package com.abbaszadeh.sanaz.covid_19.core.services

import androidx.lifecycle.MutableLiveData
import com.abbaszadeh.sanaz.covid_19.core.MyApplication
import com.facebook.flipper.plugins.network.FlipperOkhttpInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val BASE_URL = "https://corona.lmao.ninja/v2/"
    var retrofit: Retrofit? = null
    val responseStatus = MutableLiveData<Response>()

    var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    val client = OkHttpClient.Builder()
        .addNetworkInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val proceed = chain.proceed(request = chain.request())
                responseStatus.postValue(proceed)
                return proceed
            }
        })

        .addNetworkInterceptor(FlipperOkhttpInterceptor(MyApplication.networkFlipperPlugin))
        .addInterceptor(logging)
        .build()

    fun getRetrifitInstance(): Retrofit {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }

}