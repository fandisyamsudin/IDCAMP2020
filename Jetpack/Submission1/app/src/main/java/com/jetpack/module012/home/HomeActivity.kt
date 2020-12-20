package com.jetpack.module012.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.jetpack.module012.R
import com.rbddevs.splashy.Splashy
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initPageSectionAdapter()
        initSplash()
        actionChangeLanguage()
    }

    private fun actionChangeLanguage() {
        action_change_language.setOnClickListener{
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }
    }

    private fun initSplash() {
        Splashy(this)
                .setLogo(R.drawable.ic_movie)
                .setTitle(resources.getString(R.string.app_name))
                .setTitleColor(R.color.white)
                .setTitleSize(24F)
                .setLogoWHinDp(100, 100)
                .setSubTitle(resources.getString(R.string.subtitle_splash))
                .setSubTitleSize(16F)
                .setSubTitleColor(R.color.white)
                .setProgressColor(R.color.white)
                .setBackgroundResource(R.color.purple_500)
                .setFullScreen(true)
                .showLogo(true)
                .showProgress(false)
                .setDuration(3000)
                .setAnimation(Splashy.Animation.GROW_LOGO_FROM_CENTER, 800)
                .show()

        Splashy.onComplete(object : Splashy.OnComplete {
            override fun onComplete() {
                Toasty.success(this@HomeActivity, resources.getString(R.string.title_splash), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initPageSectionAdapter() {
        val sectionPagerAdapter = PageSectionAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}