package com.abbaszadeh.sanaz.covid_19.corona.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CountryInfo

@Dao
interface CoronaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCoronas(vararg coronaNetworkItem: CoronaNetworkItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountriesInfo(vararg countryInfo: CountryInfo)

    @Query("SELECT * FROM corona_table ORDER BY todayDeaths DESC")
    fun getAllCountries(): List<CompleteInfo>

    @Query("SELECT * FROM corona_table WHERE countryId = :countryId")
    fun getCountryInfo(countryId: Int): CompleteInfo

    @Query("SELECT SUM(cases) AS cases, SUM(deaths) AS deaths, SUM(recovered) AS recovered, SUM(active) AS active, SUM(critical) AS critical, MAX(updated) AS updated FROM corona_table")
    fun getGeneralStatistics(): LiveData<GeneralStatistics>

    @Query("SELECT DISTINCT continent FROM corona_table")
    fun getAllContinent(): List<String>


}