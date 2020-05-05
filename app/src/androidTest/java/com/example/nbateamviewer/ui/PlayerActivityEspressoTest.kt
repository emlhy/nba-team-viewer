package com.example.nbateamviewer.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.nbateamviewer.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Assert.*
import org.junit.ClassRule
import org.junit.Rule
import org.junit.Test

class PlayerActivityEspressoTest{

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
        val recyclerView = onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.teamRecyclerView),
                childAtPosition(
                    ViewMatchers.withClassName(Matchers.`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    2
                )
            )
        )
        recyclerView.perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )

        try {
            Thread.sleep(1000)
        } catch (e: InterruptedException){
            e.printStackTrace()
        }
        onView(withId(R.id.playerRecyclerView))
            .check(matches(isDisplayed()))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}