package com.idcamp2020.made.favorite.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idcamp2020.made.R
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.ui.MovieAdapter
import com.idcamp2020.made.favorite.databinding.FragmentFavoriteBinding
import com.idcamp2020.made.favorite.di.favoriteModule
import com.idcamp2020.made.ui.detail.DetailFragment
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules


class FavoriteFragment : Fragment() {
    private var _fragmentFavoriteBinding: FragmentFavoriteBinding? = null
    private val binding get() = _fragmentFavoriteBinding
    private lateinit var movieAdapter: MovieAdapter
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentFavoriteBinding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadKoinModules(favoriteModule)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        favoriteViewModel.getMovieFavorite()
            .observe(viewLifecycleOwner, showLayoutObserver)

        binding?.rvFavorite?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        movieAdapter.onItemClick = { selectedData ->
            val mBundle = Bundle()
            mBundle.putParcelable(DetailFragment.EXTRA_MOVIE, selectedData)
            view?.findNavController()?.navigate(R.id.action_nav_favorite_to_nav_detail, mBundle)
        }
    }

    private val showLayoutObserver = Observer<List<Movie>> {
        if (it.isNotEmpty()) {
            showProgressBar()
            showError(false)
            showRecyclerView(true)
        } else {
            showProgressBar()
            showError(true)
            showRecyclerView(false)
        }

        movieAdapter.apply {
            setData(it)
            notifyDataSetChanged()
        }
    }

    private fun showError(state: Boolean) {
        if (state) {
            binding?.empty?.main?.visibility = View.VISIBLE
        } else {
            binding?.empty?.main?.visibility = View.GONE
        }
    }

    private fun showProgressBar() {
        binding?.pbFavorite?.visibility = View.GONE
    }

    private fun showRecyclerView(state: Boolean) {
        if (state) {
            binding?.rvFavorite?.visibility = View.VISIBLE
        } else {
            binding?.rvFavorite?.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentFavoriteBinding = null
    }
}