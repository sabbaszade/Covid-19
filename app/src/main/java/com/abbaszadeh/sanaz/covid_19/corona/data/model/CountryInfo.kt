package com.abbaszadeh.sanaz.covid_19.corona.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries_table")
data class CountryInfo(
    @PrimaryKey val _id: Int,
    val flag: String,
    val iso2: String?,
    val iso3: String?,
    val latitude: Int,
    val longitude: Int
)