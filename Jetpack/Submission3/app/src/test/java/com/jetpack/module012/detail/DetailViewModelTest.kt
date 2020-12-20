package com.jetpack.module012.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.ui.detail.DetailViewModel
import com.jetpack.module012.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private val detailMovieDummy = DataDummy.dummyMovie()[0]
    private val detailTVDummy = DataDummy.dummyTvShow()[0]
    private val movieId = detailMovieDummy.id
    private val tvId = detailTVDummy.id

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observerMovie: Observer<MovieEntity>

    @Mock
    private lateinit var observerTV: Observer<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = DetailViewModel(catalogRepository)
    }

    @Test
    fun testObserveDetailMovies() {
        val movies = MutableLiveData<MovieEntity>()
        movies.value = detailMovieDummy
        `when`(catalogRepository.getMovieById(movieId)).thenReturn(movies)

        val movieDetail = viewModel.getDetailMovies(movieId).value as MovieEntity
        TestCase.assertNotNull(movieDetail)
        TestCase.assertEquals(detailMovieDummy.id, movieDetail.id)
        TestCase.assertEquals(detailMovieDummy.title, movieDetail.title)
        TestCase.assertEquals(detailMovieDummy.score, movieDetail.score)
        TestCase.assertEquals(detailMovieDummy.release, movieDetail.release)
        TestCase.assertEquals(detailMovieDummy.language, movieDetail.language)
        TestCase.assertEquals(detailMovieDummy.overview, movieDetail.overview)
        TestCase.assertEquals(detailMovieDummy.poster, movieDetail.poster)
        TestCase.assertEquals(detailMovieDummy.backdrop, movieDetail.backdrop)

        viewModel.getDetailMovies(movieId).observeForever(observerMovie)
        verify(observerMovie).onChanged(detailMovieDummy)
    }

    @Test
    fun testObserveDetailTV() {
        val tvShows = MutableLiveData<TVShowEntity>()
        tvShows.value = detailTVDummy
        `when`(catalogRepository.getTVById(tvId)).thenReturn(tvShows)

        val tvDetail = viewModel.getDetailTVSHows(tvId).value as TVShowEntity
        TestCase.assertNotNull(tvDetail)
        TestCase.assertEquals(detailTVDummy.id, tvDetail.id)
        TestCase.assertEquals(detailTVDummy.title, tvDetail.title)
        TestCase.assertEquals(detailTVDummy.score, tvDetail.score)
        TestCase.assertEquals(detailTVDummy.release, tvDetail.release)
        TestCase.assertEquals(detailTVDummy.language, tvDetail.language)
        TestCase.assertEquals(detailTVDummy.overview, tvDetail.overview)
        TestCase.assertEquals(detailTVDummy.poster, tvDetail.poster)
        TestCase.assertEquals(detailTVDummy.backdrop, tvDetail.backdrop)

        viewModel.getDetailTVSHows(tvId).observeForever(observerTV)
        verify(observerTV).onChanged(detailTVDummy)
    }
}