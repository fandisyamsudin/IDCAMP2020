package com.bfaa.favoriteapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bfaa.favoriteapp.R
import com.bfaa.favoriteapp.databinding.ActivityMainBinding
import com.bfaa.favoriteapp.ui.base.BaseActivity
import com.rbddevs.splashy.Splashy
import es.dmoral.toasty.Toasty

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    lateinit var sweetAlertDialog: SweetAlertDialog
    override var getLayoutId: Int = R.layout.activity_main
    override var getViewModel: Class<MainViewModel> = MainViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDialog()
        setSplashy()
        setToolbar()
        setUpNavigation()
    }

    private fun initDialog() {
        sweetAlertDialog = SweetAlertDialog(this).apply {
            setCancelable(false)
        }
    }

    private fun setSplashy() {
        Splashy(this)
            .setLogo(R.drawable.github)
            .setTitle(resources.getString(R.string.app_name))
            .setTitleColor(R.color.textWhite)
            .setTitleSize(24F)
            .setLogoWHinDp(100, 100)
            .setSubTitle(resources.getString(R.string.subtitle_splash))
            .setSubTitleSize(16F)
            .setSubTitleColor(R.color.textWhite)
            .setProgressColor(R.color.white)
            .setBackgroundResource(R.color.colorPrimary)
            .setFullScreen(true)
            .showLogo(true)
            .showProgress(false)
            .setDuration(5000)
            .setAnimation(Splashy.Animation.GROW_LOGO_FROM_CENTER, 800)
            .show()

        Splashy.onComplete(object : Splashy.OnComplete {
            override fun onComplete() {
                Toasty.success(this@MainActivity, resources.getString(R.string.title_splash), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun setUpNavigation() {
        val navController = findNavController(R.id.main_nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    private fun setToolbar() {
        mViewBinding.apply {
            setSupportActionBar(mainToolbar)
            mainToolbarTittle.text = getString(R.string.app_name)
        }
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun onSupportNavigateUp(): Boolean =
        findNavController(R.id.main_nav_host_fragment).navigateUp()

    override fun onBackPressed() {
        supportFragmentManager.apply {
            if (backStackEntryCount > 0)
                popBackStack()
            else
                super.onBackPressed()
        }
    }
}
