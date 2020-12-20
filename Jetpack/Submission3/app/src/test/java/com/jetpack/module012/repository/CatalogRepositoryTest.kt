package com.jetpack.module012.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.jetpack.module012.data.local.LocalDataSource
import com.jetpack.module012.data.remote.RemoteDataSource
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.utils.DataDummy
import com.jetpack.module012.utils.LiveDataTest
import com.jetpack.module012.utils.PagedListTest
import com.jetpack.module012.vo.Resource
import junit.framework.TestCase
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CatalogRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteDataSource = Mockito.mock(RemoteDataSource::class.java)
    private val localDataSource = Mockito.mock(LocalDataSource::class.java)
    private val catalogRepository = CatalogRepository(remoteDataSource, localDataSource)

    private val movies = DataDummy.dummyMovie()
    private val listMovies = DataDummy.dummyMovie()[0]

    private val tvShows = DataDummy.dummyTvShow()
    private val listTV = DataDummy.dummyTvShow()[0]

    @Test
    fun getMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(localDataSource.getMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getMovies()

        val movieEntity = Resource.success(PagedListTest.mockPagedList(DataDummy.dummyMovie()))
        Mockito.verify(localDataSource).getMovies()
        TestCase.assertNotNull(movieEntity.data)
        TestCase.assertEquals(movies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        Mockito.`when`(localDataSource.getFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteMovies()

        val movieEntity = Resource.success(PagedListTest.mockPagedList(DataDummy.dummyMovie()))
        Mockito.verify(localDataSource).getFavoriteMovies()
        TestCase.assertNotNull(movieEntity.data)
        TestCase.assertEquals(movies.size.toLong(), movieEntity.data?.size?.toLong())
    }

    @Test
    fun getMovieById() {
        val dummyMovie = MutableLiveData<MovieEntity>()
        dummyMovie.value = listMovies
        Mockito.`when`(localDataSource.getMovieById(listMovies.id)).thenReturn(dummyMovie)

        val dataMovie = LiveDataTest.getValue(catalogRepository.getMovieById(listMovies.id))
        Mockito.verify(localDataSource).getMovieById(listMovies.id)
        TestCase.assertNotNull(dataMovie)
        assertEquals(listMovies.id, dataMovie.id)
    }

    @Test
    fun getTVShow() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(localDataSource.getTVShows()).thenReturn(dataSourceFactory)
        catalogRepository.getTVShow()

        val tvEntity = Resource.success(PagedListTest.mockPagedList(DataDummy.dummyTvShow()))
        Mockito.verify(localDataSource).getTVShows()
        TestCase.assertNotNull(tvEntity.data)
        TestCase.assertEquals(tvShows.size.toLong(), tvEntity.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTV() {
        val dataSourceFactory =
            Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TVShowEntity>
        Mockito.`when`(localDataSource.getFavoriteTV()).thenReturn(dataSourceFactory)
        catalogRepository.getFavoriteTV()

        val tvEntity = Resource.success(PagedListTest.mockPagedList(DataDummy.dummyTvShow()))
        Mockito.verify(localDataSource).getFavoriteTV()
        TestCase.assertNotNull(tvEntity.data)
        TestCase.assertEquals(tvShows.size.toLong(), tvEntity.data?.size?.toLong())
    }

    @Test
    fun getTVById() {
        val dummyTV = MutableLiveData<TVShowEntity>()
        dummyTV.value = listTV
        Mockito.`when`(localDataSource.getTVById(listTV.id)).thenReturn(dummyTV)

        val dataTV = LiveDataTest.getValue(catalogRepository.getTVById(listTV.id))
        Mockito.verify(localDataSource).getTVById(listTV.id)
        TestCase.assertNotNull(dataTV)
        assertEquals(listTV.id, dataTV.id)
    }
}