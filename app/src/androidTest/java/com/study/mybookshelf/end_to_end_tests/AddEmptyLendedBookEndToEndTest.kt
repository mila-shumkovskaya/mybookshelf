package com.study.mybookshelf.end_to_end_tests

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.study.mybookshelf.MainActivity
import com.study.mybookshelf.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AddEmptyLendedBookEndToEndTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun addEmptyLendedBookTest() {
        val tabView = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withContentDescription("Lended books"),
                childAtPosition(
                    childAtPosition(
                        ViewMatchers.withId(R.id.tab_layout),
                        0
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        tabView.perform(scrollTo(), click())

        val floatingActionButton = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.fab),
                ViewMatchers.withParent(ViewMatchers.withId(R.id.lended_books_fragment)),
                ViewMatchers.isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        Thread.sleep(700)

        //Espresso.pressBack()

        val editText_title = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.et_title),
                ViewMatchers.withText(""),
                ViewMatchers.withHint(R.string.hint_title),
                ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(LinearLayout::class.java)))
            )
        )
        editText_title.perform(scrollTo())
        editText_title.check(ViewAssertions.matches(ViewMatchers.withText("")))
        editText_title.check(ViewAssertions.matches(ViewMatchers.withHint(R.string.hint_title)))

        val editText_author = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.et_author),
                ViewMatchers.withText(""),
                ViewMatchers.withHint(R.string.hint_author),
                ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(LinearLayout::class.java)))
            )
        )
        editText_author.perform(scrollTo())
        editText_author.check(ViewAssertions.matches(ViewMatchers.withText("")))
        editText_author.check(ViewAssertions.matches(ViewMatchers.withHint(R.string.hint_author)))

        val ratingBar = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.rating_bar),
                ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(LinearLayout::class.java)))
            )
        )
        ratingBar.perform(scrollTo())
        ratingBar.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val switch = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.switch_is_el), ViewMatchers.isChecked(),
                ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(LinearLayout::class.java)))
            )
        )
        switch.perform(scrollTo())
        switch.check(ViewAssertions.matches(ViewMatchers.isChecked()))

        val editText_comment = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.et_comment),
                ViewMatchers.withText(""),
                ViewMatchers.withHint(R.string.hint_comment),
                ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(LinearLayout::class.java)))
            )
        )
        editText_comment.perform(scrollTo())
        editText_comment.check(ViewAssertions.matches(ViewMatchers.withText("")))
        editText_comment.check(ViewAssertions.matches(ViewMatchers.withHint(R.string.hint_comment)))

        val editText_recipient = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.et_recipient),
                ViewMatchers.withText(""),
                ViewMatchers.withHint(R.string.hint_recipient),
                ViewMatchers.withParent(ViewMatchers.withParent(IsInstanceOf.instanceOf(LinearLayout::class.java)))
            )
        )
        editText_recipient.perform(scrollTo())
        editText_recipient.check(ViewAssertions.matches(ViewMatchers.withText("")))
        editText_recipient.check(ViewAssertions.matches(ViewMatchers.withHint(R.string.hint_recipient)))

        val transferDatePicker = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.transfer_date_picker),
                ViewMatchers.withParent(IsInstanceOf.instanceOf(LinearLayout::class.java))
            )
        )
        transferDatePicker.perform(scrollTo())
        transferDatePicker.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        val returnDatePicker = Espresso.onView(
            Matchers.allOf(
                ViewMatchers.withId(R.id.return_date_picker),
                ViewMatchers.withParent(IsInstanceOf.instanceOf(LinearLayout::class.java))
            )
        )
        returnDatePicker.perform(scrollTo())
        returnDatePicker.check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
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