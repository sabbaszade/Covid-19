package com.abbaszadeh.sanaz.covid_19.corona.data

import com.abbaszadeh.sanaz.covid_19.core.MyApplication
import com.abbaszadeh.sanaz.covid_19.core.db.CoronaDatabase
import com.abbaszadeh.sanaz.covid_19.corona.data.database.CompleteInfo
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem

class CoronaLocalDataSource {

    val db = CoronaDatabase.getDatabase(MyApplication.app)


    suspend fun insertAllCountries(list: ArrayList<CoronaNetworkItem>) {

        list.forEach { corona ->
            db.coronaDao().insertCoronas(corona)

            corona.countryInfo?.let {
                db.coronaDao().insertCountriesInfo(it)
            }
        }
    }

    fun getAllCountries(): List<CompleteInfo> {
        return db.coronaDao().getAllCountries()
    }

    fun getCountryInfo(countryId: Int): CompleteInfo {
        return db.coronaDao().getCountryInfo(countryId)
    }
}