package com.study.mybookshelf


import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class DeleteBookTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun deleteBookTest() {
        val recyclerView = mActivityTestRule.activity.findViewById<RecyclerView>(R.id.recycler_view)
        val position = 3
        val cardView =  recyclerView.layoutManager?.findViewByPosition(position);
        val titleText = cardView?.findViewById<TextView>(R.id.text_book_title)?.text
        val authorText = cardView?.findViewById<TextView>(R.id.text_book_author)?.text

        onView(
            allOf(
                withId(R.id.recycler_view),
                childAtPosition(
                    withClassName(`is`("android.widget.LinearLayout")),
                    0
                )
            )
        ).perform(actionOnItemAtPosition<ViewHolder>(position, click()))

        // check if image with basket for deleting exist
        val imageButton = onView(
            allOf(
                withId(R.id.bt_delete),
                withParent(withParent(withId(R.id.lb_details))),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val btnDelete = onView(
            allOf(
                withId(R.id.bt_delete),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.lb_details),
                        1
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        btnDelete.perform(click())

        val linearLayoutCompat = onView(
            allOf(
                withId(R.id.parentPanel),
                withParent(
                    allOf(
                        withId(android.R.id.content),
                        withParent(withId(R.id.action_bar_root))
                    )
                ),
                isDisplayed()
            )
        )
        linearLayoutCompat.check(matches(isDisplayed()))

        // check delete message
        val textView = onView(
            allOf(
                withId(android.R.id.message),
                withParent(withParent(withId(R.id.scrollView))),
                isDisplayed()
            )
        )
        textView.check(matches(withText(R.string.del_message)))

        val yesButton = onView(
            allOf(
                withId(android.R.id.button1), withText("Yes"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.buttonPanel),
                        0
                    ),
                    3
                )
            )
        )
        yesButton.perform(scrollTo(), click())

        val tvTitle = onView(
            allOf(
                withId(R.id.text_book_title), withText(titleText.toString()),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        tvTitle.check(doesNotExist())

        val tvAuthor = onView(
            allOf(
                withId(R.id.text_book_author), withText(authorText.toString()),
                withParent(withParent(withId(R.id.card_view))),
                isDisplayed()
            )
        )
        tvAuthor.check(doesNotExist())
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
