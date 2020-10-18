package com.abbaszadeh.sanaz.covid_19.corona.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abbaszadeh.sanaz.covid_19.corona.data.CoronaRepository
import com.abbaszadeh.sanaz.covid_19.corona.data.database.GeneralStatistics
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoronaViewModel(val coronaRepository: CoronaRepository) : ViewModel() {

    private var _dataLoaded = MutableLiveData<Boolean>(false)
    val dataLoaded: LiveData<Boolean>
        get() = _dataLoaded

    private var _allCountries = MutableLiveData<List<CoronaNetworkItem>>()
    val allCountries: LiveData<List<CoronaNetworkItem>>
        get() = _allCountries

    private var _infoByCountryId = MutableLiveData<CoronaNetworkItem>()
    val infoByCountryId: LiveData<CoronaNetworkItem>
        get() = _infoByCountryId

    private var _generalStatistics = coronaRepository.getGeneralStatistics()
    val generalStatistics: LiveData<GeneralStatistics>
        get() = _generalStatistics

    fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val res = coronaRepository.loadData()
            if (res.data == true) {
                _dataLoaded.postValue(true)
            }
            getAllCountries()
        }
    }

    fun getAllCountries() {
        viewModelScope.launch(Dispatchers.IO) {
            _allCountries.postValue(coronaRepository.getAllCountries())
        }
    }

    fun getCountryInfo(countryId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _infoByCountryId.postValue((coronaRepository.getCountryInfo(countryId)))
        }
    }


}