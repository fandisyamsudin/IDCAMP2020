package com.idcamp2020.made.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.idcamp2020.made.R
import com.idcamp2020.made.core.BuildConfig
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.databinding.FragmentDetailBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private var _fragmentDetailBinding: FragmentDetailBinding? = null
    private val binding get() = _fragmentDetailBinding
    private val detailViewodel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bundleData()
    }

    private fun bundleData() {
        val detailMovie = arguments?.getParcelable<Movie>(EXTRA_MOVIE)
        if (detailMovie != null){
            showDetail(detailMovie)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentDetailBinding = null
    }

    private fun showDetail(movie: Movie){
        binding?.apply{
            tvTitleDetail.text = movie.title
            tvReleaseDetail.text = movie.releaseDate
            tvVoteDetail.text = movie.voteAverage.toString()
            tvOverviewDetail.text = movie.overview

            Glide.with(this@DetailFragment)
                    .load(BuildConfig.IMAGE_URL + movie.posterPath)
                    .dontTransform()
                    .into(ivPosterDetail)

            Glide.with(this@DetailFragment)
                    .load(BuildConfig.IMAGE_URL + movie.backdropPath)
                    .dontTransform()
                    .into(ivBackdropDetail)
        }
    }
}