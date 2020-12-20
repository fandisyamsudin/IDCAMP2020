package com.jetpack.module012.ui.detail

import android.content.Intent
import android.content.Intent.createChooser
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jetpack.module012.R
import com.jetpack.module012.data.Movie
import com.jetpack.module012.data.TVShow
import com.jetpack.module012.databinding.ActivityDetailBinding
import com.jetpack.module012.utils.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityDetailBinding


    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        initToolbar()
        observeDetail()
        actionShare()
    }

    private fun observeDetail() {
        val viewModel = ViewModelProvider(
                this@DetailActivity,
                factory
        )[DetailViewModel::class.java]
        pb_detail.visibility = View.VISIBLE

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(EXTRA_MOVIE, ignoreCase = true)) {
            viewModel.getDetailMovies(id).observe(this, Observer {
                showDetailMovie(it)
                pb_detail.visibility = View.GONE
            })

        } else if (type.equals(EXTRA_TV, ignoreCase = true)) {
            viewModel.getDetailTVSHows(id).observe(this, Observer {
                showDetailTV(it)
                pb_detail.visibility = View.GONE
            })
        }
    }

    private fun showDetailTV(it: TVShow?) {
        binding.tvShows = it
    }

    private fun showDetailMovie(it: Movie?) {
        binding.movies = it
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun actionShare() {
        action_share.setOnClickListener {
            val name = tv_description?.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, name)
            intent.type = "text/plain"
            startActivity(createChooser(intent, resources.getString(R.string.share_action)))
        }
    }
}
