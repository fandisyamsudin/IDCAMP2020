package com.jetpack.module012.movie

import junit.framework.TestCase
import org.junit.Test
import org.junit.Before

class MovieViewModelTest {
    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp(){
        viewModel = MovieViewModel()
    }

    @Test
    fun observeMovie() {
        val movieEntities = viewModel.observeMovie()
        TestCase.assertNotNull(movieEntities)
        TestCase.assertEquals(10, movieEntities.size)
    }
}