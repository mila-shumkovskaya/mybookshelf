package com.study.mybookshelf.end_to_end_tests


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.study.mybookshelf.MainActivity
import com.study.mybookshelf.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class ChooseTabEndToEndTests {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun chooseLibraryTabTest() {
        val tabView = onView(
            allOf(
                withContentDescription("Library"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        tabView.perform(scrollTo(), click())

        val linearLayout = onView(
            allOf(
                withId(R.id.recycler_view_library_books),
                withParent(withParent(withId(R.id.library_books_fragment))),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))
    }

    @Test
    fun chooseBorrowedBooksTabTest() {
        val tabView = onView(
            allOf(
                withContentDescription("Borrowed books"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_layout),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        tabView.perform(scrollTo(), click())

        val linearLayout = onView(
            allOf(
                withId(R.id.recycler_view_borrowed_books),
                withParent(withParent(withId(R.id.borrowed_books_fragment))),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))
    }

    @Test
    fun chooseLendedBooksTabTest() {
        val tabView = onView(
            allOf(
                withContentDescription("Lended books"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tab_layout),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        tabView.perform(scrollTo(), click())

        val linearLayout = onView(
            allOf(
                withId(R.id.recycler_view_lended_books),
                withParent(withParent(withId(R.id.lended_books_fragment))),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))
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
