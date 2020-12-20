package com.jetpack.module012.ui.detail

import android.content.Intent
import android.content.Intent.createChooser
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.jetpack.module012.R
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.databinding.ActivityDetailBinding
import com.jetpack.module012.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "extra_tv"
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var viewModel: DetailViewModel
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
        viewModel = ViewModelProvider(
            this@DetailActivity,
            factory
        )[DetailViewModel::class.java]
        binding.pbDetail.visibility = View.VISIBLE
        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)
        if (type.equals(EXTRA_MOVIE, ignoreCase = true)) {
            viewModel.getDetailMovies(id).observe(this, {
                showDetailMovie(it)
                binding.pbDetail.visibility = View.GONE
            })

        } else if (type.equals(EXTRA_TV, ignoreCase = true)) {
            viewModel.getDetailTVSHows(id).observe(this, {
                showDetailTV(it)
                binding.pbDetail.visibility = View.GONE
            })
        }
    }

    private fun showDetailTV(tvShowEntity: TVShowEntity?) {
        binding.tvShows = tvShowEntity
        val statusTV = tvShowEntity?.favorite

        statusTV?.let { status ->
            setFavoriteStatus(status)
        }

        binding.actionFavorite.setOnClickListener {
            setFavorite(null, tvShowEntity)
        }
    }

    private fun showDetailMovie(movieEntity: MovieEntity?) {
        binding.movies = movieEntity
        val statusMovie = movieEntity?.favorite

        statusMovie?.let { status ->
            setFavoriteStatus(status)
        }

        binding.actionFavorite.setOnClickListener {
            setFavorite(movieEntity, null)
        }
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
        binding.actionShare.setOnClickListener {
            val name = tv_description?.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, name)
            intent.type = "text/plain"
            startActivity(createChooser(intent, resources.getString(R.string.share_action)))
        }
    }

    private fun setFavoriteStatus(status: Boolean) {
        if (status) {
            binding.actionFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.actionFavorite.setImageResource(R.drawable.ic_not_favorite)
        }
    }

    private fun setFavorite(movieEntity: MovieEntity?, tvShowEntity: TVShowEntity?) {
        if (movieEntity != null) {
            if (movieEntity.favorite) {
                Toasty.warning(
                    this,
                    movieEntity.title + " " + resources.getString(R.string.remove_favorite),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toasty.success(
                    this,
                    movieEntity.title + " " + resources.getString(R.string.add_favorite),
                    Toast.LENGTH_SHORT
                ).show()
            }
            viewModel.setFavoriteMovie(movieEntity)
        } else {
            if (tvShowEntity != null) {
                if (tvShowEntity.favorite) {
                    Toasty.warning(
                        this,
                        tvShowEntity.title + " " + resources.getString(R.string.remove_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toasty.success(
                        this,
                        tvShowEntity.title + " " + resources.getString(R.string.add_favorite),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                viewModel.setFavoriteTV(tvShowEntity)
            }
        }
    }
}
