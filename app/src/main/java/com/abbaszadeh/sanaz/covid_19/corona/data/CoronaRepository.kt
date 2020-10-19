package com.abbaszadeh.sanaz.covid_19.corona.data

import androidx.lifecycle.LiveData
import com.abbaszadeh.sanaz.covid_19.core.public.PublicFunc
import com.abbaszadeh.sanaz.covid_19.core.resource.Resource
import com.abbaszadeh.sanaz.covid_19.core.resource.Status
import com.abbaszadeh.sanaz.covid_19.corona.data.database.GeneralStatistics
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CoronaRepository {

    private val remoteDataSource by lazy {
        CoronaRemoteDataSource()

    }
    private val localDataSource by lazy {
        CoronaLocalDataSource()
    }


    suspend fun loadData(): Resource<Boolean> {
        var result = Resource(Status.ERROR, false, "")
        withContext(Dispatchers.IO) {
            if (PublicFunc.verifyAvailableNetwork()) {
                val res = updateCountriesFromRemote()
                if (res?.status == Status.SUCCESS) {
                    result = Resource(Status.SUCCESS, true, "")
                    return@withContext result
                } else {
                    result = Resource(Status.ERROR, false, "")

                }
            } else {

            }
        }
        return result
    }


    suspend fun updateCountriesFromRemote(): Resource<ArrayList<CoronaNetworkItem>>? =
        withContext(Dispatchers.IO) {
            val resource = remoteDataSource.getAllCountries()
            if (resource.status == Status.SUCCESS) {
                localDataSource.deleteAll()
                resource.data?.let { arrayList ->
                    localDataSource.insertAllCountries(arrayList.map {
                        CoronaNetworkItem(
                            it.active,
                            it.activePerOneMillion,
                            it.cases,
                            it.casesPerOneMillion,
                            it.continent,
                            it.country,
                            it.critical,
                            it.criticalPerOneMillion,
                            it.deaths,
                            it.deathsPerOneMillion,
                            it.oneCasePerPeople,
                            it.oneDeathPerPeople,
                            it.oneTestPerPeople,
                            it.population,
                            it.recovered,
                            it.recoveredPerOneMillion,
                            it.tests,
                            it.testsPerOneMillion,
                            it.todayCases,
                            it.todayDeaths,
                            it.todayRecovered,
                            it.updated,
                            it.countryInfo?._id
                        ).apply {
                            countryInfo = it.countryInfo
                        }
                    } as ArrayList<CoronaNetworkItem>)
                }
            } else if (resource.status == Status.ERROR) {
                return@withContext resource
            }

            return@withContext resource
        }

    fun getAllCountries(): List<CoronaNetworkItem>? {
        return localDataSource.getAllCountries().map {
            it.toCorona()
        }
    }

    fun getCountryInfo(countryId: Int): CoronaNetworkItem? {
        return localDataSource.getCountryInfo(countryId).toCorona()
    }

    fun getGeneralStatistics(): LiveData<GeneralStatistics> {
        return localDataSource.getGeneralStatistics()
    }

    fun searhCountries(search: String): List<CoronaNetworkItem>? {
        return localDataSource.searchCountries(search).map {
            it.toCorona()
        }
    }
}