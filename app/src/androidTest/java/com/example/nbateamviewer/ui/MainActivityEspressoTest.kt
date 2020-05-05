package com.example.nbateamviewer.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.nbateamviewer.R
import org.junit.Rule
import org.junit.Test


class MainActivityEspressoTest{

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java, true, false)

    @Test
    fun test_isListVisible() {
        activityRule.launchActivity(null)
        try {
            Thread.sleep(2000)
        } catch (e: InterruptedException){
            e.printStackTrace()
        }
        onView(withId(R.id.teamRecyclerView))
            .check(matches(isDisplayed()))
    }
}