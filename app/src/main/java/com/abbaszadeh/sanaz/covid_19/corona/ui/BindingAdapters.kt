package com.abbaszadeh.sanaz.covid_19.corona.ui

import androidx.databinding.BindingAdapter
import com.abbaszadeh.sanaz.covid_19.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageUrl")
fun bindImage(imgView: CircleImageView, imgUrl: String?) {

    Glide.with(imgView.context)
        .load(imgUrl)
        .placeholder(R.drawable.splash)
        .error(R.drawable.ic_broken_image)
        .into(imgView)

}