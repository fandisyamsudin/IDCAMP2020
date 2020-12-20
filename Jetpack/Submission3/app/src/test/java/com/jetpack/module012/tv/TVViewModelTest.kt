package com.jetpack.module012.tv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.jetpack.module012.data.repository.CatalogRepository
import com.jetpack.module012.data.room.TVShowEntity
import com.jetpack.module012.ui.tv.TVViewModel
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
class TVViewModelTest {
    private lateinit var viewModel: TVViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogRepository: CatalogRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TVShowEntity>>>

    @Mock
    private lateinit var tvPagedList: PagedList<TVShowEntity>

    @Before
    fun setUp() {
        viewModel = TVViewModel(catalogRepository)
    }

    @Test
    fun testObserveTV() {
        val dummyTV = Resource.success(tvPagedList)
        `when`(dummyTV.data?.size).thenReturn(10)
        val tvShows = MutableLiveData<Resource<PagedList<TVShowEntity>>>()
        tvShows.value = dummyTV

        `when`(catalogRepository.getTVShow()).thenReturn(tvShows)
        val listTV = viewModel.getTVShows().value?.data
        verify(catalogRepository).getTVShow()
        TestCase.assertNotNull(listTV)
        TestCase.assertEquals(10, listTV?.size)
        viewModel.getTVShows().observeForever(observer)
        verify(observer).onChanged(dummyTV)
    }
}