package com.abbaszadeh.sanaz.covid_19.corona.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.abbaszadeh.sanaz.covid_19.R
import com.abbaszadeh.sanaz.covid_19.corona.data.CoronaRepository
import com.abbaszadeh.sanaz.covid_19.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: CoronaViewModel by viewModels {
        CoronaViewModelFactory(CoronaRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.coronaViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val args: DetailsFragmentArgs by navArgs()
        val countryId = args.countryId

        viewModel.getCountryInfo(countryId)

        binding.arrowId.setOnClickListener {
            findNavController().navigateUp()
        }
        return binding.root
    }


}