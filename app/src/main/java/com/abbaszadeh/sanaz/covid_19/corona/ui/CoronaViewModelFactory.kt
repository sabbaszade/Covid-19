package com.abbaszadeh.sanaz.covid_19.corona.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.abbaszadeh.sanaz.covid_19.corona.data.CoronaRepository

class CoronaViewModelFactory(private val coronaRepository: CoronaRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CoronaViewModel::class.java)) {
            return CoronaViewModel(coronaRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}