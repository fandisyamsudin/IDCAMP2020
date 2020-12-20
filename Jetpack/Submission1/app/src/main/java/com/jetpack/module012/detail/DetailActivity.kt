package com.jetpack.module012.detail

import android.content.Intent
import android.content.Intent.createChooser
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jetpack.module012.R
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV = "estra_tv"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()
        observeDetail()
        actionShare()
    }

    private fun actionShare() {
        action_share.setOnClickListener{
            val name = tv_description?.text.toString()
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, name)
            intent.type = "text/plain"
            startActivity(createChooser(intent, resources.getString(R.string.share_action)))
        }
    }

    private fun observeDetail() {
        val detailViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[DetailViewModel::class.java]
        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            detailViewModel.selectDetailMovie(movieId)
            detailViewModel.observeDetailMovies().let {
                if (it != null){
                    tv_genre.text = it.genre
                    tv_duration.text = it.duration
                    tv_release.text = it.release
                    tv_score.text = it.score
                    tv_description.text = it.description
                    toolbar.title = it.title

                    Glide.with(this)
                        .load(it.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.poster_aquaman))
                        .error(R.drawable.ic_error)
                        .dontTransform()
                        .into(iv_poster)
                }
            }
            val tvId = extras.getInt(EXTRA_TV)
            detailViewModel.selectDetailTV(tvId)
            detailViewModel.observeDetailTV().let {
                if (it != null){
                    tv_genre.text = it.genre
                    tv_duration.text = it.duration
                    tv_release.text = it.release
                    tv_score.text = it.score
                    tv_description.text = it.description
                    toolbar.title = it.title

                    Glide.with(this)
                        .load(it.poster)
                        .apply(RequestOptions.placeholderOf(R.drawable.poster_aquaman))
                        .error(R.drawable.ic_error)
                        .dontTransform()
                        .into(iv_poster)
                }
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
