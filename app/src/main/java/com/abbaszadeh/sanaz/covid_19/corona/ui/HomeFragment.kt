package com.abbaszadeh.sanaz.covid_19.corona.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

        //for search
        binding.searchBoxId.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    viewModel.searchCountries(newText)

                } else {
                    viewModel.getAllCountries()
                }
                return false
            }
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
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvList.setHasFixedSize(true)
        /*    rvList.addItemDecoration(
                DividerItemDecoration(
                    context,
                    LinearLayoutManager.VERTICAL
                )
            )*/

            binding.rvList.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {

                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {}

                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                    when (e.action) {
                        MotionEvent.ACTION_DOWN -> {
                            rvList.parent?.requestDisallowInterceptTouchEvent(true)
                        }
                    }
                    return false
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {}
            })
        }

    }

    override fun onRowClick(pos: Int, corona: CoronaNetworkItem) {
        findNavController().navigate(HomeFragmentDirections.actionGlobalDetailsFragment(corona.countryId!!))
    }

}