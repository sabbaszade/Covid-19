package com.abbaszadeh.sanaz.covid_19.corona.ui

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.abbaszadeh.sanaz.covid_19.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*
import java.util.concurrent.TimeUnit

@BindingAdapter("imageUrl")
fun bindImage(imgView: CircleImageView, imgUrl: String?) {

    Glide.with(imgView.context)
        .load(imgUrl)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)
        .into(imgView)

}

@BindingAdapter("bindServerDate")
fun bindServerDate(textView: TextView, date: Long?) {
    if (date == null)
        return
    val currentTime: Date = Calendar.getInstance().time
    val different: Long = (currentTime.time - date)
    val days = TimeUnit.MILLISECONDS.toDays(different)
    val hours = TimeUnit.MILLISECONDS.toHours(different)
    val minutes = TimeUnit.MILLISECONDS.toMinutes(different)
    when (minutes) {
        0L -> textView.text = "آخرین آپدیت : همین لحظه"
        in 1L..60L -> textView.text = "آخرین آپدیت : $minutes دقیقه پیش"
        in 61L..1440L -> textView.text = "آخرین آپدیت : $hours ساعت پیش"
        else -> textView.text = "آخرین آپدیت : $days روز پیش"
    }

}

@BindingAdapter("imageUrl2")
fun bindImage2(imgView: ImageView, imgUrl: String?) {

    Glide.with(imgView.context)
        .load(imgUrl)
        .placeholder(R.drawable.loading_animation)
        .error(R.drawable.ic_broken_image)
        .into(imgView)

}