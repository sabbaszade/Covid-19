package com.abbaszadeh.sanaz.covid_19.corona.ui

import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbaszadeh.sanaz.covid_19.corona.data.model.CoronaNetworkItem
import com.abbaszadeh.sanaz.covid_19.databinding.LayoutRvHomeBinding

class HomeAdapter(
    val countryList: List<CoronaNetworkItem>,
    var clicklistener: HomeFrgClickListener? = null
) :
    RecyclerView.Adapter<HomeAdapter.myViewHolder>() {

    inner class myViewHolder(val binding: LayoutRvHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.card.setOnClickListener {
                clicklistener?.onRowClick(layoutPosition, countryList[layoutPosition])
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val binding = LayoutRvHomeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        // 75 percent of screen
        val widthScreen = Resources.getSystem().getDisplayMetrics().widthPixels
        binding.card.layoutParams = binding.card.layoutParams.apply {
            width = (widthScreen * 0.60).toInt()
        }

        return myViewHolder(
            binding
        )
    }

    override fun getItemCount() = countryList.size

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        val currentItem = countryList[position]
        holder.binding.corona = currentItem

    }

}