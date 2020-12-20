package com.jetpack.module012.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jetpack.module012.R
import com.jetpack.module012.ui.splash.SplashActivity
import com.jetpack.module012.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(SplashActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun detailMovie() {
        Thread.sleep(3000)
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        10
                )
        )
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.detail)).perform(ViewActions.swipeDown())
        pressBack()
        onView(withId(R.id.main)).perform(ViewActions.swipeDown())
        onView(withId(R.id.main)).perform(ViewActions.swipeUp())
    }

    @Test
    fun detailTV(){
        Thread.sleep(3000)
        onView(withText(R.string.tab_tvshow)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
                RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                        10
                )
        )
        onView(withId(R.id.rv_tv)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.collapse_toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_backdrop)).check(matches(isDisplayed()))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_language)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_vote_average)).check(matches(isDisplayed()))
        onView(withId(R.id.detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.detail)).perform(ViewActions.swipeDown())
        pressBack()
        onView(withId(R.id.main)).perform(ViewActions.swipeDown())
        onView(withId(R.id.main)).perform(ViewActions.swipeUp())
    }

    @Test
    fun actionShare() {
        Thread.sleep(3000)
        onView(withId(R.id.rv_movie)).perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                        0,
                        ViewActions.click()
                )
        )
        onView(withId(R.id.action_share)).perform(ViewActions.click())
    }
}