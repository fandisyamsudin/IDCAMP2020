package com.jetpack.module012.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.ui.favorite.tv.TVFavoriteViewModel
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
class TVFavoriteViewModelTest {
    private lateinit var viewModel: TVFavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TVShowEntity>>

    @Mock
    private lateinit var tvPagedList: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = TVFavoriteViewModel(catalogRepository)
    }

    @Test
    fun getFavoriteTV() {
        val dummyTV = tvPagedList
        `when`(dummyTV.size).thenReturn(10)
        val tvShows = MutableLiveData<PagedList<TVShowEntity>>()
        tvShows.value = dummyTV

        `when`(catalogRepository.getFavoriteTV()).thenReturn(tvShows)
        val tvEntity = viewModel.getFavoriteTV().value
        verify(catalogRepository).getFavoriteTV()
        TestCase.assertNotNull(tvEntity)
        TestCase.assertEquals(10, tvEntity?.size)

        viewModel.getFavoriteTV().observeForever(observer)
        verify(observer).onChanged(dummyTV)
    }
}