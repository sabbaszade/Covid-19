package com.abbaszadeh.sanaz.covid_19.corona.data.database

import androidx.room.Embedded
import androidx.room.Relation
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CountryInfo

/** CompleteInfo **/

data class CompleteInfo(
    @Embedded val coronaNetworkItem: CoronaNetworkItem,
    @Relation(
        entity = CountryInfo::class,
        parentColumn = "countryId",
        entityColumn = "_id"
    )
    val countryInfo: CountryInfo
) {
    fun toCorona(): CoronaNetworkItem {
        return coronaNetworkItem.copy().apply {
            countryInfo = this@CompleteInfo.countryInfo
        }
    }
}

/** General statistics **/
data class GeneralStatistics(
    val cases: Int,
    val deaths: Int,
    val recovered: Int,
    val active: Int,
    val critical: Int,
    val updated: Long
)