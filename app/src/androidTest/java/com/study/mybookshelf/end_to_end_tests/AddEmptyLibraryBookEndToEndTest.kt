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
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddEmptyLibraryBookEndToEndTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addEmptyLibraryBookTest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.fab),
                withParent(withId(R.id.library_books_fragment)),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        //pressBack()

        val editText_title = onView(
            allOf(
                withId(R.id.et_title), withText(""), withHint(
                    R.string.hint_title
                ),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java)))
            )
        )
        editText_title.perform(scrollTo())
        editText_title.check(matches(withText("")))
        editText_title.check(matches(withHint(R.string.hint_title)))

        val editText_author = onView(
            allOf(
                withId(R.id.et_author), withText(""), withHint(
                    R.string.hint_author
                ),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java)))
            )
        )
        editText_author.perform(scrollTo())
        editText_author.check(matches(withText("")))
        editText_author.check(matches(withHint(R.string.hint_author)))

        val ratingBar = onView(
            allOf(
                withId(R.id.rating_bar),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java)))
            )
        )
        ratingBar.perform(scrollTo())
        ratingBar.check(matches(isDisplayed()))

        val switch = onView(
            allOf(
                withId(R.id.switch_is_el), isChecked(),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java)))
            )
        )
        switch.perform(scrollTo())
        switch.check(matches(isChecked()))

        val editText_comment = onView(
            allOf(
                withId(R.id.et_comment), withText(""), withHint(
                    R.string.hint_comment
                ),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java)))
            )
        )
        editText_comment.perform(scrollTo())
        editText_comment.check(matches(withText("")))
        editText_comment.check(matches(withHint(R.string.hint_comment)))
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