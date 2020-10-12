package com.abbaszadeh.sanaz.covid_19.home.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.abbaszadeh.sanaz.covid_19.corona.ui.HomeFragment
import com.abbaszadeh.sanaz.covid_19.corona.ui.MyCountryFragment

class StateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    var fragments: ArrayList<Fragment> = arrayListOf(
        HomeFragment(),
        MyCountryFragment()

    )

    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}