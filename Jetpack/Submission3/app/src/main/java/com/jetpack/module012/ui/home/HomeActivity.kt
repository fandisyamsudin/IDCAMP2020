package com.jetpack.module012.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jetpack.module012.R
import com.jetpack.module012.databinding.ActivityHomeBinding
import com.jetpack.module012.ui.favorite.FavoriteActivity
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : DaggerAppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var sweetAlertDialog: SweetAlertDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initDialog()
        initPageSectionAdapter()
        goFavoriteFragment()
    }

    private fun goFavoriteFragment() {
        binding.actionFavorite.setOnClickListener {
            val intent = Intent(this@HomeActivity, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initDialog() {
        sweetAlertDialog = SweetAlertDialog(this).apply {
            setCancelable(false)
        }
    }

    private fun initPageSectionAdapter() {
        val sectionPagerAdapter = PageSectionAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tabs.setupWithViewPager(view_pager)
        supportActionBar?.elevation = 0f
    }
}