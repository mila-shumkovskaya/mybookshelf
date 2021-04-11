package com.study.mybookshelf.integration_tests

import android.content.Intent
import android.os.Bundle
import android.support.test.InstrumentationRegistry
import androidx.core.graphics.drawable.toBitmap
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import androidx.test.runner.AndroidJUnit4
import com.study.mybookshelf.DetailsActivity
import com.study.mybookshelf.R
import com.study.mybookshelf.model.LibraryBook
import com.study.mybookshelf.ui.fragments.CoverDialogFragment
import com.study.mybookshelf.ui.fragments.LibraryBookDetailsFragment
import junit.framework.Assert.assertNotSame
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(AndroidJUnit4::class)
class CoverFromCameraTest {

    /*@Rule
    @JvmField
    val detailsActivityTestRule = ActivityTestRule(DetailsActivity::class.java)*/

    @get:Rule
    val detailsActivityTestRule: ActivityTestRule<DetailsActivity> =
        object : ActivityTestRule<DetailsActivity>(DetailsActivity::class.java) {
            override fun getActivityIntent(): Intent {
                val targetContext = InstrumentationRegistry.getInstrumentation().targetContext
                val intent = Intent(targetContext, DetailsActivity::class.java)
                val book = LibraryBook(1, "title", "author", ByteArray(0), 5.0.toFloat(), true, "comments")
                intent.putExtra("book", book)
                return intent
            }
        }

    @Rule
    @JvmField
    var grantPermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
            "android.permission.CAMERA",
            "android.permission.READ_EXTERNAL_STORAGE"
        )

    @Test
    fun getCoverFromCameraTest() {
        /*val libraryBookDetailsFragment = LibraryBookDetailsFragment()
        detailsActivityTestRule.activity.supportFragmentManager.beginTransaction().add(R.id.container, libraryBookDetailsFragment).commit()
        val book = LibraryBook(1, "title", "author", ByteArray(0), 5.0.toFloat(), true, "comments")
        libraryBookDetailsFragment.book=book
        //setCoverToImageView(libraryBookDetailsFragment.ivCover, book)
        detailsActivityTestRule.activity.myFragment = libraryBookDetailsFragment
        val coverDialogFragment = CoverDialogFragment(detailsActivityTestRule.activity, libraryBookDetailsFragment.book.title, libraryBookDetailsFragment.book.author)
        val oldBitmap = libraryBookDetailsFragment.ivCover.drawable.toBitmap()
        coverDialogFragment.getFromCamera()
        val newBitmap = libraryBookDetailsFragment.ivCover.drawable.toBitmap()
        assertNotSame(oldBitmap, newBitmap)
         */
    }
}