package com.jetpack.module012.movie


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

import com.jetpack.module012.data.CatalogRepository
import com.jetpack.module012.data.Movie
import com.jetpack.module012.ui.movie.MovieViewModel
import com.jetpack.module012.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {
    private val dummyMovie = DataDummy.dummyMovie()
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<Movie>>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun observeMovie() {
        val movies = MutableLiveData<List<Movie>>()
        movies.value = dummyMovie
        Mockito.`when`(catalogRepository.getMovies()).thenReturn(movies)

        val listMovie = viewModel.getMovies().value
        verify(catalogRepository).getMovies()
        TestCase.assertNotNull(listMovie)
        TestCase.assertEquals(10, listMovie?.size)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}