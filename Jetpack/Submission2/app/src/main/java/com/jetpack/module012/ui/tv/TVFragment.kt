package com.jetpack.module012.ui.tv

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
import com.jetpack.module012.data.TVShow
import com.jetpack.module012.ui.detail.DetailActivity
import com.jetpack.module012.utils.ViewModelFactory
import dagger.android.support.DaggerFragment
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_tv.*
import javax.inject.Inject

class TVFragment : DaggerFragment() {
    private lateinit var viewModel: TVViewModel
    private val tvAdapter = TVAdapter()

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        intentDetailTV()
    }

    private fun intentDetailTV() {
        tvAdapter.setOnItemClickCallback(object : TVAdapter.OnItemClickCallback {
            override fun onItemClicked(tvShow: TVShow) {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, tvShow.id)
                intent.putExtra(DetailActivity.EXTRA_TYPE, DetailActivity.EXTRA_TV)
                startActivity(intent)

                context?.let {
                    Toasty.success(
                            it,
                            resources.getString(R.string.detail) + " " + tvShow.title,
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
            )[TVViewModel::class.java]
        }
        pb_tv.visibility = View.VISIBLE
        viewModel.getTVShows().observe(viewLifecycleOwner, Observer { listTvShow ->
            tvAdapter.setTVShows(listTvShow)
            pb_tv.visibility = View.GONE
        })
    }

    private fun initRecyclerView() {
        with(rv_tv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }
}