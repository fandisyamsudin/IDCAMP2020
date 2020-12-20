package com.jetpack.module012.detail

import com.jetpack.module012.utils.DataDummy
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class DetailViewModelTest{
    private lateinit var viewModel: DetailViewModel
    private val detailMovieDummy = DataDummy.dummyMovie()[0]
    private val detailTVDummy = DataDummy.dummyTvShow()[0]
    private val movieId = detailMovieDummy.id
    private val tvId = detailTVDummy.id

    @Before
    fun setUp(){
        viewModel = DetailViewModel()
        viewModel.selectDetailMovie(movieId)
        viewModel.selectDetailTV(tvId)
    }

    @Test
    fun testObserveDetailMovies() {
        viewModel.selectDetailMovie(detailMovieDummy.id)
        val movieEntity = viewModel.observeDetailMovies()
        TestCase.assertNotNull(movieEntity)
        TestCase.assertEquals(detailMovieDummy.id, movieEntity?.id)
        TestCase.assertEquals(detailMovieDummy.poster, movieEntity?.poster)
        TestCase.assertEquals(detailMovieDummy.title, movieEntity?.title)
        TestCase.assertEquals(detailMovieDummy.score, movieEntity?.score)
        TestCase.assertEquals(detailMovieDummy.release, movieEntity?.release)
        TestCase.assertEquals(detailMovieDummy.duration, movieEntity?.duration)
        TestCase.assertEquals(detailMovieDummy.genre, movieEntity?.genre)
    }

    @Test
    fun testObserveDetailTV() {
        viewModel.selectDetailTV(detailTVDummy.id)
        val tvEntity = viewModel.observeDetailTV()
        TestCase.assertNotNull(tvEntity)
        TestCase.assertEquals(detailTVDummy.id, tvEntity?.id)
        TestCase.assertEquals(detailTVDummy.poster, tvEntity?.poster)
        TestCase.assertEquals(detailTVDummy.title, tvEntity?.title)
        TestCase.assertEquals(detailTVDummy.score, tvEntity?.score)
        TestCase.assertEquals(detailTVDummy.release, tvEntity?.release)
        TestCase.assertEquals(detailTVDummy.duration, tvEntity?.duration)
        TestCase.assertEquals(detailTVDummy.genre, tvEntity?.genre)
    }

}