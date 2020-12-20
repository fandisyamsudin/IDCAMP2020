package com.jetpack.module012.ui.tv

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
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.databinding.FragmentTvBinding
import com.jetpack.module012.ui.detail.DetailActivity
import com.jetpack.module012.ui.home.HomeActivity
import com.jetpack.module012.utils.showLoadingError
import com.jetpack.module012.viewmodel.ViewModelFactory
import com.jetpack.module012.vo.Status
import dagger.android.support.DaggerFragment
import es.dmoral.toasty.Toasty
import javax.inject.Inject

class TVFragment : DaggerFragment() {
    private lateinit var viewModel: TVViewModel
    private lateinit var sweetAlertDialog: SweetAlertDialog
    private val tvAdapter = TVAdapter()

    @Inject
    lateinit var factory: ViewModelFactory
    private lateinit var binding: FragmentTvBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tv, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initViewModel()
        initDialog()
        intentDetailTV()
    }

    private fun intentDetailTV() {
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

    private fun initViewModel() {
        activity?.let {
            viewModel = ViewModelProvider(
                it,
                factory
            )[TVViewModel::class.java]
        }
        viewModel.getTVShows().observe(viewLifecycleOwner, { listTV ->
            if (listTV != null) {
                when (listTV.status) {
                    Status.LOADING -> binding.apply {
                        pbTv.visibility = View.VISIBLE
                        rvTv.visibility = View.GONE
                        timeOut.main.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.apply {
                            pbTv.visibility = View.GONE
                            rvTv.visibility = View.VISIBLE
                            timeOut.main.visibility = View.GONE
                        }
                        with(tvAdapter) {
                            submitList(listTV.data)
                            notifyDataSetChanged()
                        }
                    }
                    Status.ERROR -> {
                        binding.apply {
                            pbTv.visibility = View.GONE
                            rvTv.visibility = View.GONE
                            timeOut.main.visibility = View.VISIBLE
                        }
                        showLoadingError(sweetAlertDialog, null)
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        with(binding.rvTv) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }

    private fun initDialog() {
        sweetAlertDialog = (activity as HomeActivity).sweetAlertDialog
    }
}