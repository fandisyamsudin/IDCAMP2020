package com.jetpack.module012.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.jetpack.module012.data.CatalogRepository
import com.jetpack.module012.data.TVShow
import com.jetpack.module012.ui.tv.TVViewModel
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
class TVViewModelTest {
    private val dummyTV = DataDummy.dummyTvShow()
    private lateinit var viewModel: TVViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<List<TVShow>>

    @Before
    fun setUp() {
        viewModel = TVViewModel(catalogRepository)
    }

    @Test
    fun testObserveTV() {
        val tvShows = MutableLiveData<List<TVShow>>()
        tvShows.value = dummyTV
        Mockito.`when`(catalogRepository.getTVShow()).thenReturn(tvShows)

        val listTV = viewModel.getTVShows().value
        verify(catalogRepository).getTVShow()
        TestCase.assertNotNull(listTV)
        TestCase.assertEquals(10, listTV?.size)
        viewModel.getTVShows().observeForever(observer)
        verify(observer).onChanged(dummyTV)
    }
}