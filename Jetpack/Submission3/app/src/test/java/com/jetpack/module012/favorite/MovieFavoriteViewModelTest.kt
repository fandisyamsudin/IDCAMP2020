package com.jetpack.module012.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.ui.favorite.movie.MovieFavoriteViewModel
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavoriteViewModelTest {

    private lateinit var viewModel: MovieFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieFavoriteViewModel(catalogRepository)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovie = moviePagedList
        `when`(dummyMovie.size).thenReturn(10)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(catalogRepository.getFavoriteMovies()).thenReturn(movies)
        val movieEntity = viewModel.getFavoriteMovies().value
        verify(catalogRepository).getFavoriteMovies()
        TestCase.assertNotNull(movieEntity)
        TestCase.assertEquals(10, movieEntity?.size)

        viewModel.getFavoriteMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}