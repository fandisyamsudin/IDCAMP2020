package com.jetpack.module012.movie


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.MovieEntity
import com.jetpack.module012.ui.movie.MovieViewModel
import com.jetpack.module012.vo.Resource
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
class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var moviePagedList: PagedList<MovieEntity>

    @Before
    fun setUp() {
        viewModel = MovieViewModel(catalogRepository)
    }

    @Test
    fun observeMovie() {
        val dummyMovie = Resource.success(moviePagedList)
        `when`(dummyMovie.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(catalogRepository.getMovies()).thenReturn(movies)
        val listMovie = viewModel.getMovies().value?.data
        verify(catalogRepository).getMovies()
        TestCase.assertNotNull(listMovie)
        TestCase.assertEquals(10, listMovie?.size)
        viewModel.getMovies().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}