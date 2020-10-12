package com.abbaszadeh.sanaz.covid_19.corona.data.database

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
}