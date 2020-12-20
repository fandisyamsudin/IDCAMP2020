package com.jetpack.module012.ui.favorite.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.jetpack.module012.R
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.databinding.MovieFavoriteFragmentBinding
import com.jetpack.module012.ui.detail.DetailActivity
import com.jetpack.module012.ui.movie.MovieAdapter
import com.jetpack.module012.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import es.dmoral.toasty.Toasty
import javax.inject.Inject

class MovieFavoriteFragment : DaggerFragment() {
    private lateinit var viewModel: MovieFavoriteViewModel
    private val movieAdapter = MovieAdapter()

    private lateinit var binding: MovieFavoriteFragmentBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.movie_favorite_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        observeMovies()
        intentDetailMovie()
    }

    private fun intentDetailMovie() {
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: MovieEntity) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movie.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.EXTRA_MOVIE)
                startActivity(intent)

                context?.let {
                    Toasty.info(
                        it,
                        resources.getString(R.string.detail) + " " + movie.title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun observeMovies() {
        viewModel.getFavoriteMovies().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                if (listMovie.isNullOrEmpty()) {
                    binding.apply {
                        rvMovieFavorite.visibility = View.GONE
                        emptyFavorite.main.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        rvMovieFavorite.visibility = View.VISIBLE
                        emptyFavorite.main.visibility = View.GONE
                    }
                    with(movieAdapter) {
                        submitList(listMovie)
                        notifyDataSetChanged()
                    }
                }
            }
        })
    }

    private fun initViewModel() {
        activity?.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[MovieFavoriteViewModel::class.java]
        }
    }

    private fun initRecyclerView() {
        with(binding.rvMovieFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}