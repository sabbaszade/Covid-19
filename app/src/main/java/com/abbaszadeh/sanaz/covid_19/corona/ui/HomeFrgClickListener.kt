package com.abbaszadeh.sanaz.covid_19.corona.ui

import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem

interface HomeFrgClickListener {

    fun onRowClick(pos: Int, corona: CoronaNetworkItem)
}