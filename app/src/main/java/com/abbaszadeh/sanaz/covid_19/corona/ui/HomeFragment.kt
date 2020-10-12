package com.abbaszadeh.sanaz.covid_19.corona.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.abbaszadeh.sanaz.covid_19.R
import com.abbaszadeh.sanaz.covid_19.corona.data.CoronaRepository
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem
import com.abbaszadeh.sanaz.covid_19.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), HomeFrgClickListener {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: CoronaViewModel by viewModels {
        CoronaViewModelFactory(CoronaRepository())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.coronaViewModel = viewModel

        viewModel.getAllCountries()
        viewModel.allCountries.observe(viewLifecycleOwner, Observer {
            setRvCountries(it.toMutableList())
        })

        return binding.root
    }

    fun setRvCountries(
        list: MutableList<CoronaNetworkItem>
    ) {
        binding.rvList.adapter =
            HomeAdapter(list, this)
        binding.apply {
            rvList.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            rvList.setHasFixedSize(true)
            rvList.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )
        }

    }

    override fun onRowClick(pos: Int, corona: CoronaNetworkItem) {
        findNavController().navigate(HomeFragmentDirections.actionGlobalDetailsFragment(corona.countryId!!))
    }

}