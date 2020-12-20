package com.jetpack.module012.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jetpack.module012.R
import com.jetpack.module012.utils.DataDummy
import org.junit.Test
import org.junit.Rule

class HomeActivityTest {
    private val movieDummy = DataDummy.dummyMovie()
    private val tvDummy = DataDummy.dummyTvShow()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun loadMovie() {
        Thread.sleep(5000)
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(movieDummy.size))
        onView(withId(R.id.main)).perform(ViewActions.swipeUp())
        onView(withId(R.id.main)).perform(ViewActions.swipeDown())
    }

    @Test
    fun loadDetailMovie(){
        Thread.sleep(5000)
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText(movieDummy[0].title))));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(movieDummy[0].genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(movieDummy[0].score)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(movieDummy[0].duration)))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(movieDummy[0].release)))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(movieDummy[0].description)))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.detail)).perform(ViewActions.swipeDown())
        pressBack()
    }

    @Test
    fun loadTv() {
        Thread.sleep(5000)
        onView(withText(R.string.tab_tvshow)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(tvDummy.size))
        onView(withId(R.id.main)).perform(ViewActions.swipeUp())
        onView(withId(R.id.main)).perform(ViewActions.swipeDown())
    }

    @Test
    fun loadDetailTV(){
        Thread.sleep(5000)
        onView(withText(R.string.tab_tvshow)).perform(ViewActions.click())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.toolbar)).check(matches(hasDescendant(withText(tvDummy[0].title))));
        onView(withId(R.id.tv_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_genre)).check(matches(withText(tvDummy[0].genre)))
        onView(withId(R.id.tv_score)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_score)).check(matches(withText(tvDummy[0].score)))
        onView(withId(R.id.tv_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_duration)).check(matches(withText(tvDummy[0].duration)))
        onView(withId(R.id.tv_release)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_release)).check(matches(withText(tvDummy[0].release)))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(withText(tvDummy[0].description)))
        onView(withId(R.id.iv_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.detail)).perform(ViewActions.swipeUp())
        onView(withId(R.id.detail)).perform(ViewActions.swipeDown())
        pressBack()
    }

    @Test
    fun actionChangeLanguage(){
        Thread.sleep(5000)
        onView(withId(R.id.action_change_language)).perform(ViewActions.click())

    }

    @Test
    fun actionShare(){
        Thread.sleep(5000)
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.action_share)).perform(ViewActions.click())
    }
}