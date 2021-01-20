package com.idcamp2020.made

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.rbddevs.splashy.Splashy
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSplashy()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_favorite, R.id.nav_setting), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private fun setSplashy() {
        Splashy(this)
                .setLogo(R.drawable.ic_imdb)
                .setTitle(resources.getString(R.string.app_name))
                .setTitleColor(R.color.secondary_yellow)
                .setTitleSize(24F)
                .setLogoWHinDp(70, 70)
                .setSubTitle(resources.getString(R.string.subtitle_splash))
                .setSubTitleSize(16F)
                .setSubTitleColor(R.color.secondary_yellow)
                .setProgressColor(R.color.white)
                .setBackgroundResource(R.drawable.side_nav_bar)
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
}