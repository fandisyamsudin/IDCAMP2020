package com.jetpack.module012.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.jetpack.module012.BuildConfig
import com.jetpack.module012.R

@BindingAdapter(("imageUrl"))
fun loadImageUrl(view: ImageView, url: String?) {
    url?.let { view.loadEclipseImage(url) }
}

fun ImageView.loadEclipseImage(url: String?) {
    val loader = CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 25f
        start()
    }

    val option = RequestOptions()
        .placeholder(loader)
        .error(R.drawable.ic_error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    try {
        Glide.with(context).clear(this)
        Glide.with(context)
            .setDefaultRequestOptions(option)
            .load(BuildConfig.IMAGE_URL + url)
            .dontTransform()
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}