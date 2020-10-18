package com.abbaszadeh.sanaz.covid_19.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.abbaszadeh.sanaz.covid_19.R
import com.abbaszadeh.sanaz.covid_19.core.public.PublicFunc
import com.abbaszadeh.sanaz.covid_19.corona.data.CoronaRepository
import com.abbaszadeh.sanaz.covid_19.corona.ui.CoronaViewModel
import com.abbaszadeh.sanaz.covid_19.corona.ui.CoronaViewModelFactory
import com.abbaszadeh.sanaz.covid_19.databinding.FragmentBaseBinding
import com.google.android.material.tabs.TabLayoutMediator


class BaseFragment : Fragment() {
    private lateinit var binding: FragmentBaseBinding
    private val viewModel: CoronaViewModel by viewModels {
        CoronaViewModelFactory(CoronaRepository())
    }
    var time_cur_back: Long = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)

        initViewPager2()
        setExitApp()

        binding.updateId.setOnClickListener {
            if (PublicFunc.verifyAvailableNetwork()) {
                viewModel.loadData()
            } else {
                Toast.makeText(requireContext(), getString(R.string.wifi_on), Toast.LENGTH_SHORT)
            }
        }


        return binding.root
    }

    private fun initViewPager2() {
        var viewPager = binding.viewPagerId
        var adapter = StateAdapter(requireActivity().supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        var tabLayout = binding.tabLayout
        var names: ArrayList<String> = arrayListOf("جهان", "ایران")
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = names[position]

            //  viewPager.setPageTransformer(ZoomOutPageTransformer())

        }.attach()
    }

    //for exit app

    private fun setExitApp() {
        requireActivity().onBackPressedDispatcher.addCallback(this)
        {
            if (time_cur_back < System.currentTimeMillis()) {
                Toast.makeText(requireContext(), getString(R.string.exit_des), Toast.LENGTH_SHORT)
                    .show()

                time_cur_back = System.currentTimeMillis() + 2000
            } else {
                requireActivity().finish()
            }
        }
    }

}