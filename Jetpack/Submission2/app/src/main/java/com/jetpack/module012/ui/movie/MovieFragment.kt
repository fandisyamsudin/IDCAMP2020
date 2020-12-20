package com.jetpack.module012.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.module012.R
import com.jetpack.module012.data.Movie
import com.jetpack.module012.ui.detail.DetailActivity
import com.jetpack.module012.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.jetpack.module012.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_movie.*
import javax.inject.Inject

class MovieFragment : DaggerFragment() {
    private lateinit var viewModel: MovieViewModel
    private val movieAdapter = MovieAdapter()

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        intentDetailMovie()
    }

    private fun intentDetailMovie() {
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: Movie) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movie.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, EXTRA_MOVIE)
                startActivity(intent)

                context?.let {
                    Toasty.success(
                            it,
                            resources.getString(R.string.detail) + " " + movie.title,
                            Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun initViewModel() {
        activity?.let {
            viewModel = ViewModelProvider(
                    it,
                    factory
            )[MovieViewModel::class.java]
        }
        pb_movie.visibility = View.VISIBLE
        viewModel.getMovies().observe(viewLifecycleOwner, Observer { listMovie ->
            movieAdapter.setMovies(listMovie)
            pb_movie.visibility = View.GONE
        })
    }

    private fun initRecyclerView() {
        with(rv_movie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}