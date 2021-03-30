package com.study.mybookshelf

import android.app.Activity
import android.graphics.Bitmap
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.study.mybookshelf.google_books_api.GetCoverClass
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

const val title = "A"
const val author = "A"

@LargeTest
@RunWith(AndroidJUnit4::class)
class CoverFromInternetTest {

    @Rule
    @JvmField
    val detailsActivityTestRule = ActivityTestRule(DetailsActivity::class.java)

    @Rule
    @JvmField
    var mGrantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        "android.permission.CAMERA",
        "android.permission.READ_EXTERNAL_STORAGE"
    )

    @Test
    fun coverWithoutTitleAndAuthorTest() {
        val getCoverClass = GetCoverClass(detailsActivityTestRule.activity)
        getCoverClass.getCover("", "") { _: Activity, imageList: ArrayList<Bitmap> ->
            assertEquals(true, imageList.isEmpty())
        }
    }

    @Test
    fun coverWithoutTitleTest() {
        val getCoverClass = GetCoverClass(detailsActivityTestRule.activity)
        getCoverClass.getCover("", author) { _: Activity, imageList: ArrayList<Bitmap> ->
            assertEquals(true, imageList.isEmpty())
        }
    }

    @Test
    fun coverWithoutAuthorTest() {
        val getCoverClass = GetCoverClass(detailsActivityTestRule.activity)
        getCoverClass.getCover(title, "") { _: Activity, imageList: ArrayList<Bitmap> ->
            assertEquals(false, imageList.isNullOrEmpty())
        }
    }

    @Test
    fun coverWithTitleAndAuthorTest() {
        val getCoverClass = GetCoverClass(detailsActivityTestRule.activity)
        getCoverClass.getCover(title, author) { _: Activity, imageList: ArrayList<Bitmap> ->
            assertEquals(false, imageList.isNullOrEmpty())
        }
    }

}