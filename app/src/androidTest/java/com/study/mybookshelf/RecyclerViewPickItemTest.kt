package com.study.mybookshelf


import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class RecyclerViewPickItemTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun pickItemOnLibraryBooksTest() {
        val layout = getLibraryTabLayout()
        checkChosenItemAtLayout(layout!!, R.id.library_books_fragment)
    }

    @Test
    fun pickItemOnBorrowedBooksTest() {
        val layout = getBorrowedBooksTabLayout()
        checkChosenItemAtLayout(layout!!, R.id.borrowed_books_fragment)
    }

    @Test
    fun pickItemOnLendedBooksTest() {
        val layout = getLendedBooksTabLayout()
        checkChosenItemAtLayout(layout!!, R.id.lended_books_fragment)
    }

    fun getLibraryTabLayout(): RelativeLayout? {
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
        tabView.perform(click())
        return mActivityTestRule.activity.findViewById<RelativeLayout>(R.id.library_books_fragment)
    }

    fun getBorrowedBooksTabLayout(): RelativeLayout? {
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
        tabView.perform(click())
        return mActivityTestRule.activity.findViewById<RelativeLayout>(R.id.borrowed_books_fragment)
    }

    fun getLendedBooksTabLayout(): RelativeLayout? {
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
        tabView.perform(click())
        return mActivityTestRule.activity.findViewById<RelativeLayout>(R.id.lended_books_fragment)
    }

    private fun checkChosenItemAtLayout(layout: RelativeLayout, fragmentId: Int) {
        val position = 1
        val recyclerView = layout.findViewById<RecyclerView>(R.id.recycler_view)

        val cardView =  recyclerView.layoutManager?.findViewByPosition(position);
        val titleText = cardView?.findViewById<TextView>(R.id.text_book_title)?.text
        val authorText = cardView?.findViewById<TextView>(R.id.text_book_author)?.text

        onView(
            allOf(
                withId(R.id.recycler_view),
                withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                withParent(withParent(withParent(withParent(withId(fragmentId)))))
            )
        ).perform(actionOnItemAtPosition<ViewHolder>(position, click()))

        val etTitle = onView(
            allOf(
                withId(R.id.et_title), withText(titleText.toString()),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        etTitle.check(matches(withText(titleText.toString())))

        val etAuthor = onView(
            allOf(
                withId(R.id.et_author), withText(authorText.toString()),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        etAuthor.check(matches(withText(authorText.toString())))
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
