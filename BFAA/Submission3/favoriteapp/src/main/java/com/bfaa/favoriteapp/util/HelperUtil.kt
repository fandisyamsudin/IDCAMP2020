package com.bfaa.favoriteapp.util

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import androidx.viewbinding.ViewBinding
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bfaa.favoriteapp.R
import com.bfaa.favoriteapp.databinding.FragmentHomeBinding
import com.bfaa.favoriteapp.databinding.FragmentProfileBinding
import com.bfaa.favoriteapp.databinding.FragmentProfileFollowBinding
import com.bfaa.favoriteapp.model.PlaceholderInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter(("imageUrl"))
fun avatarUrl(view: ImageView, url: String?) {
    url?.let { view.image(url) }
}

@BindingAdapter(("avatarId"))
fun avatarId(view: ImageView, id: Int?) {
    id?.let { view.loadImage(id) }
}

fun showDialogWarning(dialog: SweetAlertDialog, message: String, method: (() -> Unit)?) {
    dialog.apply {
        changeAlertType(SweetAlertDialog.WARNING_TYPE)
        titleText = dialog.context.getString(R.string.init_title_error)
        contentText = dialog.context.getString(R.string.init_message_error)
        setConfirmClickListener {
            method?.let {
                method.invoke()
            }
            dismiss()
        }
        if (!this.isShowing) {
            show()
        }
    }
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun ImageView.loadImage(id: Int?) {
    id?.let { this.setImageResource(it) }
}


fun ImageView.image(url: String?) {
    val loader = CircularProgressDrawable(context).apply {
        strokeWidth = 5f
        centerRadius = 20f
        start()
    }

    val option = RequestOptions()
        .error(R.drawable.ic_error_search)
        .placeholder(loader)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .circleCrop()

    try {
        Glide.with(context)
            .setDefaultRequestOptions(option)
            .load(url)
            .into(this)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun View.changeNavigation(direction: NavDirections) {
    Navigation.findNavController(this).navigate(direction)
}

fun setContentPlaceholder(condition: Int, binding: ViewBinding) {
    val data = PlaceholderInfo(null, R.drawable.ic_error_search, R.string.init_title_error, R.string.init_message_error)
    data.apply {
        when (condition) {
            1 -> {
                placeholder = false
            }
            2 -> {
                placeholder = true
                image_info = R.drawable.ic_error_search
                tittle_message = R.string.title_error
                description_message = R.string.init_message_error
            }
            3 -> {
                placeholder = true
                image_info = R.drawable.ic_init_search
                tittle_message = R.string.init_search_info
                description_message = R.string.init_search_message
            }
            4 -> {
                placeholder = true
                image_info = R.drawable.ic_not_found_search
                tittle_message = R.string.title_error
                description_message = R.string.not_found_search
            }
            5 -> {
                placeholder = true
                image_info = R.drawable.ic_not_found_search
                tittle_message = R.string.title_error
                description_message = R.string.not_found_followers
            }
            6 -> {
                placeholder = true
                image_info = R.drawable.ic_not_found_search
                tittle_message = R.string.title_error
                description_message = R.string.not_found_following
            }
            else -> {
                placeholder = true
                image_info = R.drawable.ic_not_found_search
                tittle_message = R.string.title_error
                description_message = R.string.not_found_favorite
            }
        }
    }
    when (binding) {
        is FragmentHomeBinding -> {
            binding.placeholder = data
        }
        is FragmentProfileBinding -> {
            binding.placeholder = data
        }
        is FragmentProfileFollowBinding -> {
            binding.placeholder = data
        }
    }
}


const val DATABASE_NAME = "database_favorite"
const val DATABASE_AUTHORITY = "com.bfaa.idchamp2020"
const val DATABASE_SCHEME = "content"
const val DATABASE_CONTENT_URI = "$DATABASE_SCHEME://$DATABASE_AUTHORITY"
const val USER_TABLE_NAME = "users_favorite"
const val USER_CONTENT_URI = "$DATABASE_CONTENT_URI/$USER_TABLE_NAME"
const val USER_ID = "id"
const val USER_AVATAR_URL = "avatar_url"
const val USER_COMPANY = "company"
const val USER_FOLLOWERS = "followers"
const val USER_FOLLOWERS_URL = "followers_url"
const val USER_FOLLOWING = "following"
const val USER_FOLLOWING_URL = "following_url"
const val USER_LOCATION = "location"
const val USER_LOGIN = "login"
const val USER_NAME = "name"
const val USER_TYPE = "type"

