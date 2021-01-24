package com.idcamp2020.made.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.idcamp2020.made.MainActivity
import com.idcamp2020.made.R
import com.idcamp2020.made.core.data.Resource
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.ui.MovieAdapter
import com.idcamp2020.made.databinding.FragmentHomeBinding
import com.idcamp2020.made.ui.detail.DetailFragment.Companion.EXTRA_MOVIE
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.viewmodel.ext.android.viewModel

@FlowPreview
class HomeFragment : Fragment() {
    private var _fragmentHomeBinding: FragmentHomeBinding? = null
    private val binding get() = _fragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var searchView: SearchView

    @FlowPreview
    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentHomeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        val toolbar: Toolbar = activity?.findViewById<View>(R.id.toolbar) as Toolbar
        (activity as AppCompatActivity?)?.setSupportActionBar(toolbar)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    @ExperimentalCoroutinesApi
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
        searchView = (activity as MainActivity).findViewById(R.id.search_view)
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        initSearch()
    }

    @ExperimentalCoroutinesApi
    private fun initSearch() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchViewModel.setQuerySearch(it)
                }
                return true
            }
        })
    }

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearchRecyclerView()
    }

    @ExperimentalCoroutinesApi
    private fun initSearchRecyclerView() {
        searchViewModel.resultSearch.observe(viewLifecycleOwner, {
            movieAdapter.apply {
                setData(it)
                notifyDataSetChanged()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentHomeBinding = null
    }

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter()
        homeViewModel.getMovie().observe(viewLifecycleOwner, showLayoutObserver)

        binding?.rvMovie?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }

        movieAdapter.onItemClick = { selectedData ->
            val mBundle = Bundle()
            mBundle.putParcelable(EXTRA_MOVIE, selectedData)
            view?.findNavController()?.navigate(R.id.action_nav_home_to_nav_detail, mBundle)
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

    private fun showError(state: Boolean) {
        if (state) {
            binding?.errorMovie?.main?.visibility = View.VISIBLE
        } else {
            binding?.errorMovie?.main?.visibility = View.GONE
        }
    }

    private fun showProgressBar(state: Boolean) {
        if (state) {
            binding?.pbMovie?.visibility = View.VISIBLE
        } else {
            binding?.pbMovie?.visibility = View.GONE
        }
    }

    private fun showRecyclerView(state: Boolean) {
        if (state) {
            binding?.rvMovie?.visibility = View.VISIBLE
        } else {
            binding?.rvMovie?.visibility = View.GONE
        }
    }
}