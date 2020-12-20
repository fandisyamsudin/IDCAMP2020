package com.idchamp.bfaa.list

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.idchamp.bfaa.R
import com.idchamp.bfaa.detail.DetailActivity
import com.rbddevs.splashy.Splashy
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<Users>()
    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.activity_main)
        setSplashy()
        setToolbar()
        rv_users.setHasFixedSize(true)
        list.addAll(getListUsers())
        showRecyclerGrid()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.Title)
        toolbar.setSubtitle(R.string.subtitle)
    }

    private fun setSplashy() {
        Splashy(this)
            .setLogo(R.drawable.github3)
            .setTitle(resources.getString(R.string.title_splash))
            .setTitleColor(R.color.colorTitleWhite)
            .setTitleSize(24F)
            .setLogoWHinDp(70, 70)
            .setSubTitle(resources.getString(R.string.subtitle_splash))
            .setSubTitleSize(16F)
            .setSubTitleColor(R.color.colorTitleWhite)
            .setProgressColor(R.color.white)
            .setBackgroundResource(R.color.colorAccent)
            .setFullScreen(true)
            .showLogo(true)
            .showProgress(true)
            .setDuration(5000)
            .setAnimation(Splashy.Animation.GROW_LOGO_FROM_CENTER, 800)
            .show()

        Splashy.onComplete(object : Splashy.OnComplete {
            override fun onComplete() {
                Toasty.success(this@MainActivity, resources.getString(R.string.title_splash), Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_translate) {
            val translateIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(translateIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecyclerGrid() {
        rv_users.layoutManager = GridLayoutManager(this, 2)
        val gridUserAdapter = GridAdapter(list)
        rv_users.adapter = gridUserAdapter
        gridUserAdapter.setOnItemClickCallback(object : GridAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Users) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(users: Users) {
        Toasty.info(this, resources.getString(R.string.go_detail) + " " + users.name, Toast.LENGTH_SHORT, true).show()
        val users = Users(
            users.name,
            users.username,
            users.location,
            users.repository,
            users.company,
            users.followers,
            users.following,
            users.avatar
        )
        val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
        moveWithObjectIntent.putExtra(DetailActivity.EXTRA_DETAIL, users)
        startActivity(moveWithObjectIntent)
    }

    @SuppressLint("Recycle")
    private fun getListUsers(): ArrayList<Users> {
        val dataName = resources.getStringArray(R.array.name)
        val dataUserName = resources.getStringArray(R.array.username)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataFollower = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataAvatar = resources.obtainTypedArray(R.array.avatar)

        val listUser = ArrayList<Users>()
        for (position in dataName.indices){
            val users = Users(
                dataName[position],
                dataUserName[position],
                dataLocation[position],
                dataRepository[position],
                dataCompany[position],
                dataFollower[position],
                dataFollowing[position],
                dataAvatar.getResourceId(position, -1)
            )
            listUser.add(users)
        }
        return listUser
    }
}
