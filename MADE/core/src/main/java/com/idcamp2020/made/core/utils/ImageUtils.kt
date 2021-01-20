package com.idcamp2020.made.core.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.idcamp2020.made.core.BuildConfig
import com.idcamp2020.made.core.R


fun loadImageUrl(view: ImageView, path: String?) {
    path?.let { view.loadEclipseImage(path) }
}

fun ImageView.loadEclipseImage(path: String?) {
    val loader = CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 25f
        start()
    }

    val option = RequestOptions()
        .placeholder(loader)
        .error(R.drawable.ic_not_found)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    try {
        Glide.with(context).clear(this)
        Glide.with(context)
            .setDefaultRequestOptions(option)
            .load(BuildConfig.IMAGE_URL + path)
            .dontTransform()
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}