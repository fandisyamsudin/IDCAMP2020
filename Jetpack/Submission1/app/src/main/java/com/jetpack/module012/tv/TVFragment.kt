package com.jetpack.module012.tv

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
import com.jetpack.module012.data.TVShow
import com.jetpack.module012.detail.DetailActivity
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_tv.*


class TVFragment : Fragment() {
    private val tvAdapter = TVAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
    }

    private fun initView() {
        if (activity != null){
            val tvViewModel = ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory())[TVViewModel::class.java]
            val tvShow = tvViewModel.observeTV()
            tvAdapter.setTVShows(tvShow)

            with(rv_tv){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = tvAdapter
            }

           tvAdapter.setOnItemClickCallback(object : TVAdapter.OnItemClickCallback{
                override fun onItemClicked(tvShow: TVShow) {
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_TV, tvShow.id)
                    startActivity(intent)

                    context?.let { Toasty.success(it, resources.getString(R.string.detail) + " " + tvShow.title, Toast.LENGTH_SHORT).show() }

                }
            })
        }
    }
}