package com.idcamp2020.made.ui.detail

import android.os.Bundle
import android.system.Os.remove
import android.view.*
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.idcamp2020.made.R
import com.idcamp2020.made.core.BuildConfig
import com.idcamp2020.made.core.domain.model.Movie
import com.idcamp2020.made.core.utils.loadImageUrl
import com.idcamp2020.made.databinding.FragmentDetailBinding
import es.dmoral.toasty.Toasty
import org.koin.android.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    private var _fragmentDetailBinding: FragmentDetailBinding? = null
    private val binding get() = _fragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bundleData()
    }

    private fun bundleData() {
        val detailMovie = arguments?.getParcelable<Movie>(EXTRA_MOVIE)
        if (detailMovie != null) showDetail(detailMovie)
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
            loadImageUrl(ivBackdropDetail, movie.backdropPath)
            loadImageUrl(ivPosterDetail, movie.posterPath)

            var favoriteState = movie.isFavorite
            setFavoriteStatus(favoriteState)

            binding?.btnFavorite?.setOnClickListener {
                favoriteState = !favoriteState
                detailViewModel.setMovieFavorite(movie, favoriteState)
                setFavoriteStatus(favoriteState)
            }
        }
    }

    private fun setFavoriteStatus(status: Boolean) {
        if (status){
            binding?.btnFavorite?.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.ic_favorite
                    )
                }
            )
        } else {
            binding?.btnFavorite?.setImageDrawable(
                context?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.ic_baseline_favorite_border
                    )
                }
            )
        }
    }
}