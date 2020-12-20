package com.idchamp.bfaa.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.content.Intent.createChooser
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.idchamp.bfaa.R
import com.idchamp.bfaa.list.Users
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_detail)
        getParcel()
        backButton()
        actionShare()
    }

    private fun actionShare() {
        btn_share.setOnClickListener{
            val name = tv_name.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, name)
            intent.type = "text/plain"
            startActivity(createChooser(intent, resources.getString(R.string.share_action)))
        }
    }

    private fun backButton() {
        btn_back.setOnClickListener{
            onBackPressed()
        }
    }

    private fun getParcel() {
        val users = intent.getParcelableExtra(EXTRA_DETAIL) as Users
        tv_name.text = users.name
        tv_user_name.text = users.username
        tv_location.text = users.location
        tv_repository.text = users.repository
        tv_company.text = users.company
        tv_follower.text = users.followers
        tv_following.text = users.following

        Glide.with(this)
            .load(users.avatar)
            .placeholder(R.color.black)
            .fitCenter()
            .into(img_user)
    }
}
