package com.abbaszadeh.sanaz.covid_19.corona.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.abbaszadeh.sanaz.covid_19.R
import com.abbaszadeh.sanaz.covid_19.corona.data.CoronaRepository
import com.abbaszadeh.sanaz.covid_19.databinding.FragmentMyCountryBinding


class MyCountryFragment : Fragment() {
    private lateinit var binding: FragmentMyCountryBinding
    private val viewModel: CoronaViewModel by viewModels {
        CoronaViewModelFactory(CoronaRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my_country, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.getCountryInfo(364)
        viewModel.infoByCountryId.observe(viewLifecycleOwner, Observer {
            binding.corona = it
        })

        return binding.root
    }

}