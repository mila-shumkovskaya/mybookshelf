package com.study.mybookshelf


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.study.mybookshelf.ui.fragments.SettingsFragment
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class GoToSettingsFromDrawerEndToEndTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

//    @get:Rule
//    var intentMainActivity: IntentsTestRule<MainActivity> = IntentsTestRule(MainActivity::class.java)

    @Test
    fun goToSettingsFromDrawerEndToEndTest() {
        val appCompatImageButton = onView(
            allOf(
                //withContentDescription("Открыть панель навигации"),/////////////////////////////////////////
                childAtPosition(
                    allOf(
                        withId(R.id.toolbar),
                        childAtPosition(
                            withClassName(`is`("com.google.android.material.appbar.AppBarLayout")),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())


        val navigationMenuItemView = onView(
            allOf(
                withId(R.id.nav_settings),
                childAtPosition(
                    allOf(
                        withId(R.id.design_navigation_view),
                        childAtPosition(
                            withId(R.id.nav_view),
                            0
                        )
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        navigationMenuItemView.perform(click())

        //Intents.init()
        //val fragment = mActivityTestRule.activity.supportFragmentManager.fragments[1] as SettingsFragment
        //assertEquals(MainActivity::class.java, fragment::class.java)
        //intended(hasComponent(SettingsFragment::class.java.name))///////////////////////////////////////////
        //assertEquals(mActivityTestRule.activity.supportFragmentManager.fragments[0]::class.java, SettingsFragment::class.java)

        val textView_notifications = onView(
            allOf(
                withId(android.R.id.title), withText(R.string.notif_title),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView_notifications.check(matches(withText(R.string.notif_title)))

        val textView_notifSwitch = onView(
            allOf(
                withId(android.R.id.title), withText(R.string.notif_switch),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView_notifSwitch.check(matches(withText(R.string.notif_switch)))

        val switch = onView(
            allOf(
                withId(R.id.switchWidget),
                withParent(
                    allOf(
                        withId(android.R.id.widget_frame),
                        withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        switch.check(matches(isDisplayed()))

        val textView_setNotifTime = onView(
            allOf(
                withId(android.R.id.title), withText(R.string.notif_label),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView_setNotifTime.check(matches(withText(R.string.notif_label)))

        val textView_language = onView(
            allOf(
                withId(android.R.id.title), withText(R.string.language_label),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView_language.check(matches(withText(R.string.language_label)))

        val textView_chooseLang = onView(
            allOf(
                withId(android.R.id.title), withText(R.string.language_title),
                withParent(withParent(IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java))),
                isDisplayed()
            )
        )
        textView_chooseLang.check(matches(withText(R.string.language_title)))
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
