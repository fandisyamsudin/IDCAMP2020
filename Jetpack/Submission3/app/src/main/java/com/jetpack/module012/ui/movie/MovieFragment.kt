package com.jetpack.module012.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import cn.pedant.SweetAlert.SweetAlertDialog
import com.jetpack.module012.R
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.databinding.FragmentMovieBinding
import com.jetpack.module012.ui.detail.DetailActivity
import com.jetpack.module012.ui.detail.DetailActivity.Companion.EXTRA_MOVIE
import com.jetpack.module012.ui.home.HomeActivity
import com.jetpack.module012.utils.showLoadingError
import com.jetpack.module012.viewmodel.ViewModelFactory
import com.jetpack.module012.vo.Status
import dagger.android.support.DaggerFragment
import es.dmoral.toasty.Toasty
import javax.inject.Inject

class MovieFragment : DaggerFragment() {
    private lateinit var viewModel: MovieViewModel
    private lateinit var sweetAlertDialog: SweetAlertDialog
    private val movieAdapter = MovieAdapter()

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        initDialog()
        observeMovies()
        intentDetailMovie()
    }

    private fun observeMovies() {
        viewModel.getMovies().observe(viewLifecycleOwner, { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> binding.apply {
                        pbMovie.visibility = View.VISIBLE
                        rvMovie.visibility = View.GONE
                        timeOut.main.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.apply {
                            pbMovie.visibility = View.GONE
                            rvMovie.visibility = View.VISIBLE
                            timeOut.main.visibility = View.GONE
                        }
                        with(movieAdapter) {
                            submitList(listMovie.data)
                            notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        binding.apply {
                            pbMovie.visibility = View.GONE
                            rvMovie.visibility = View.GONE
                            timeOut.main.visibility = View.VISIBLE
                        }
                        showLoadingError(sweetAlertDialog, null)
                    }
                }
            }
        })
    }

    private fun intentDetailMovie() {
        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(movie: MovieEntity) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, movie.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, EXTRA_MOVIE)
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

    private fun initViewModel() {
        activity?.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[MovieViewModel::class.java]
        }
    }

    private fun initRecyclerView() {
        with(binding.rvMovie) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun initDialog() {
        sweetAlertDialog = (activity as HomeActivity).sweetAlertDialog
    }
}