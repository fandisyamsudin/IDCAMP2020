package com.idcamp2020.made.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.idcamp2020.made.core.data.Resource
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.ui.MovieAdapter
import com.idcamp2020.made.core.utils.SortUtils
import com.idcamp2020.made.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentHomeBinding = null
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        homeViewModel.getMovie(SortUtils.NEWEST).observe(viewLifecycleOwner, showLayoutObserver)

        binding?.rvMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private val showLayoutObserver = Observer<Resource<List<Movie>>> {
        if (it != null) {
            when (it) {
                is Resource.Loading -> {
                    showProgressBar(true)
                    showError(false)
                    showRecyclerView(false)
                }
                is Resource.Success -> {
                    showProgressBar(false)
                    showError(false)
                    showRecyclerView(true)
                    movieAdapter.apply {
                        setData(it.data)
                        notifyDataSetChanged()
                    }
                }
                is Resource.Error -> {
                    showProgressBar(false)
                    showError(true)
                    showRecyclerView(false)
                }
            }
        }
    }

    private fun showError(state: Boolean){
        if (state){
            binding?.errorMovie?.main?.visibility = View.VISIBLE
        } else {
            binding?.errorMovie?.main?.visibility = View.GONE
        }
    }

    private fun showProgressBar(state: Boolean){
        if (state){
            binding?.pbMovie?.visibility = View.VISIBLE
        } else {
            binding?.pbMovie?.visibility = View.GONE
        }
    }

    private fun showRecyclerView(state: Boolean){
        if (state){
            binding?.rvMovie?.visibility = View.VISIBLE
        } else {
            binding?.rvMovie?.visibility = View.GONE
        }
    }
}