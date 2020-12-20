package com.jetpack.module012.ui.favorite.tv

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
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.databinding.TvFavoriteFragmentBinding
import com.jetpack.module012.ui.detail.DetailActivity
import com.jetpack.module012.ui.tv.TVAdapter
import com.jetpack.module012.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import es.dmoral.toasty.Toasty
import javax.inject.Inject

class TVFavoriteFragment : DaggerFragment() {
    private lateinit var viewModel: TVFavoriteViewModel
    private val tvAdapter = TVAdapter()

    private lateinit var binding: TvFavoriteFragmentBinding

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.tv_favorite_fragment, container, false)
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
        tvAdapter.setOnItemClickCallback(object : TVAdapter.OnItemClickCallback {
            override fun onItemClicked(tvShow: TVShowEntity) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, tvShow.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.EXTRA_TV)
                startActivity(intent)

                context?.let {
                    Toasty.info(
                        it,
                        resources.getString(R.string.detail) + " " + tvShow.title,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun observeMovies() {
        viewModel.getFavoriteTV().observe(viewLifecycleOwner, { listTV ->
            if (listTV != null) {
                if (listTV.isNullOrEmpty()) {
                    binding.apply {
                        rvTvFavorite.visibility = View.GONE
                        emptyFavorite.main.visibility = View.VISIBLE
                    }
                } else {
                    binding.apply {
                        rvTvFavorite.visibility = View.VISIBLE
                        emptyFavorite.main.visibility = View.GONE
                    }
                    with(tvAdapter) {
                        submitList(listTV)
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
            )[TVFavoriteViewModel::class.java]
        }
    }

    private fun initRecyclerView() {
        with(binding.rvTvFavorite) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }
}