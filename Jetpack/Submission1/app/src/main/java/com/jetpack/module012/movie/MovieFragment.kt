package com.jetpack.module012.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.module012.R
import com.jetpack.module012.data.Movie
import com.jetpack.module012.detail.DetailActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {
    private val movieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        if (activity != null){
            val movieViewModel = ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory())[MovieViewModel::class.java]
            val movies = movieViewModel.observeMovie()
            movieAdapter.setMovies(movies)

            with(rv_movie){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }

            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
                override fun onItemClicked(movie: Movie) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_MOVIE, movie.id)
                    startActivity(intent)

                    context?.let { Toasty.success(it, resources.getString(R.string.detail) + " " + movie.title, Toast.LENGTH_SHORT).show() }
                }
            })
        }
    }
}