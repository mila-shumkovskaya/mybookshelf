package com.study.mybookshelf


import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
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
class EditBookTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun editItemOnLibraryBooksTest() {
        val layout = getLibraryTabLayout()
        editBookTest(layout!!, R.id.recycler_view_library_books, R.id.bt_save4)
    }

    @Test
    fun editItemOnBorrowedBooksTest() {
        val layout = getBorrowedBooksTabLayout()
        editBookTest(layout!!, R.id.recycler_view_borrowed_books, R.id.bt_save)
    }

    @Test
    fun editItemOnLendedBooksTest() {
        val layout = getLendedBooksTabLayout()
        editBookTest(layout!!, R.id.recycler_view_lended_books, R.id.bt_save2)
    }

    private fun getLibraryTabLayout(): RelativeLayout? {
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
        return mActivityTestRule.activity.findViewById<RelativeLayout>(R.id.library_books_fragment)
    }

    private fun getBorrowedBooksTabLayout(): RelativeLayout? {
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
        return mActivityTestRule.activity.findViewById<RelativeLayout>(R.id.borrowed_books_fragment)
    }

    private fun getLendedBooksTabLayout(): RelativeLayout? {
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
        return mActivityTestRule.activity.findViewById<RelativeLayout>(R.id.lended_books_fragment)
    }

    private fun editBookTest(layout: RelativeLayout, id: Int, idBtnSave: Int) {
        val recyclerView = layout.findViewById<RecyclerView>(R.id.recycler_view)
        val position = 1
        val cardView =  recyclerView.layoutManager?.findViewByPosition(position);
        val oldTitle = cardView?.findViewById<TextView>(R.id.text_book_title)?.text
        val oldAuthor = cardView?.findViewById<TextView>(R.id.text_book_author)?.text
        val newTitle = "A_new_book"
        val newAuthor = "A_new_author"

        onView(
            allOf(
                withId(R.id.recycler_view),
                withClassName(`is`("androidx.recyclerview.widget.RecyclerView")),
                withParent(withParent(withId(id)))
            )
        ).perform(scrollToPosition<ViewHolder>(position), actionOnItemAtPosition<ViewHolder>(position, click()))

        val btnEdit = onView(
            allOf(
                withId(R.id.bt_edit),
                isDisplayed()
            )
        )
        btnEdit.perform(click())

        val etTitle = onView(
            allOf(
                withId(R.id.et_title), withText(oldTitle.toString()),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        1
                    ),
                    1
                )
            )
        )
        etTitle.perform(replaceText(newTitle))


        val etAuthor = onView(
            allOf(
                withId(R.id.et_author), withText(oldAuthor.toString()),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.LinearLayout")),
                        2
                    ),
                    1
                )
            )
        )
        etAuthor.perform(replaceText(newAuthor))

        val btnSave = onView(
            allOf(
                withId(idBtnSave), withText("Save changes"),
                isDisplayed()
            )
        )
        btnSave.perform(click())

        val tvNewTitle = onView(
            allOf(
                withId(R.id.text_book_title), withText(newTitle),
                withParent(withParent(withId(R.id.card_view)))
            )
        )
        tvNewTitle.check(matches(isDisplayed()))

        val tvNewAuthor = onView(
            allOf(
                withId(R.id.text_book_author), withText(newAuthor),
                withParent(withParent(withId(R.id.card_view)))
            )
        )
        tvNewAuthor.check(matches(isDisplayed()))

        val tvOldTitle = onView(
            allOf(
                withId(R.id.text_book_title), withText(oldTitle.toString()),
                withParent(withParent(withId(R.id.card_view)))
            )
        )
        tvOldTitle.check(ViewAssertions.doesNotExist())

        val tvOldAuthor = onView(
            allOf(
                withId(R.id.text_book_author), withText(oldAuthor.toString()),
                withParent(withParent(withId(R.id.card_view)))
            )
        )
        tvOldAuthor.check(ViewAssertions.doesNotExist())
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
