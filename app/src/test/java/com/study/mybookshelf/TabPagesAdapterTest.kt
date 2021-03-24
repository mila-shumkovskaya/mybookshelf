package com.study.mybookshelf

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


class TabPagesAdapterTest {
    @Rule @JvmField
   val testRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
    }

    @Test
    fun testGetItemCount() {

    }

    @Test
    fun testClickOnTab() {
      /*  assertEquals(tabAdapter.createFragment(0), LibraryFragment())
        assertEquals(tabAdapter.createFragment(1), BorrowedBooksFragment())
        assertEquals(tabAdapter.createFragment(2), LendedBooksFragment())*/
        onView(withId(R.id.tab_layout)).perform(click())
    }
}