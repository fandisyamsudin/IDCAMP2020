package com.jetpack.module012.tv

import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class TVViewModelTest {
    private lateinit var viewModel: TVViewModel

    @Before
    fun setUp(){
        viewModel = TVViewModel()
    }

    @Test
    fun testObserveTV() {
        val tvEntities = viewModel.observeTV()
        TestCase.assertNotNull(tvEntities)
        TestCase.assertEquals(10, tvEntities.size)
    }
}