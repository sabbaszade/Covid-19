package com.abbaszadeh.sanaz.covid_19.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abbaszadeh.sanaz.covid_19.corona.data.database.CoronaDao
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CountryInfo

@Database(entities = arrayOf(CoronaNetworkItem::class, CountryInfo::class), version = 1)
abstract class CoronaDatabase : RoomDatabase() {
    abstract fun coronaDao(): CoronaDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CoronaDatabase? = null

        fun getDatabase(context: Context): CoronaDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CoronaDatabase::class.java,
                    "corona_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}