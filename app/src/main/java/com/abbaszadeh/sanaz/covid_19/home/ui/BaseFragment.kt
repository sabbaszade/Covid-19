package com.abbaszadeh.sanaz.covid_19.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.abbaszadeh.sanaz.covid_19.R
import com.abbaszadeh.sanaz.covid_19.databinding.FragmentBaseBinding
import com.google.android.material.tabs.TabLayoutMediator


class BaseFragment : Fragment() {
    private lateinit var binding: FragmentBaseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_base, container, false)

        initViewPager2()

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

            viewPager.setPageTransformer(ZoomOutPageTransformer())

        }.attach()
    }

}