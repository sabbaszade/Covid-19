package com.abbaszadeh.sanaz.covid_19.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.abbaszadeh.sanaz.covid_19.R
import com.abbaszadeh.sanaz.covid_19.corona.data.CoronaRepository
import com.abbaszadeh.sanaz.covid_19.corona.ui.CoronaViewModel
import com.abbaszadeh.sanaz.covid_19.corona.ui.CoronaViewModelFactory
import com.abbaszadeh.sanaz.covid_19.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {

    private lateinit var binding: FragmentSplashBinding
    private val viewModel: CoronaViewModel by viewModels {
        CoronaViewModelFactory(CoronaRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_splash, container, false)

        viewModel.loadData()
        viewModel.dataLoaded.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToBaseFragment())
            } else {
                viewModel.allCountries.observe(viewLifecycleOwner, Observer {
                    if (it.isNullOrEmpty()) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.wifi_on),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToBaseFragment())
                    }
                })

            }
        })


        return binding.root
    }


}