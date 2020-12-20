package com.jetpack.module012.ui.favorite

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.jetpack.module012.R
import com.jetpack.module012.databinding.ActivityFavoriteBinding
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class FavoriteActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_favorite)
        initPageSectionAdapter()
    }

    private fun initPageSectionAdapter() {
        val sectionPagerAdapter = FavoriteSectionAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}