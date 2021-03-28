package com.study.mybookshelf

import com.study.mybookshelf.utils.notGreaterThanDate
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class ExtensionsCalendarUnitTest {

    private var cDate: Calendar = Calendar.getInstance()
    private var cMustBeGreaterDate: Calendar = Calendar.getInstance()

    @Before
    fun setUp() {
        cMustBeGreaterDate.set(Calendar.YEAR, 2021)
        cMustBeGreaterDate.set(Calendar.MONTH, 11)
        cMustBeGreaterDate.set(Calendar.DAY_OF_MONTH, 3)
    }

    @Test
    fun testNotGreaterThanDate_less() {
        cDate.set(Calendar.YEAR, 2021)
        cDate.set(Calendar.MONTH, 11)
        cDate.set(Calendar.DAY_OF_MONTH, 2)
        assertTrue(cDate.notGreaterThanDate(cMustBeGreaterDate))
    }

    @Test
    fun testNotGreaterThanDate_equal() {
        cDate = cMustBeGreaterDate
        assertTrue(cDate.notGreaterThanDate(cMustBeGreaterDate))
    }

    @Test
    fun testNotGreaterThanDate_greaterDay() {
        cDate.set(Calendar.YEAR, 2021)
        cDate.set(Calendar.MONTH, 11)
        cDate.set(Calendar.DAY_OF_MONTH, 5)
        assertFalse(cDate.notGreaterThanDate(cMustBeGreaterDate))
    }

    @Test
    fun testNotGreaterThanDate_greaterMonth() {
        cDate.set(Calendar.YEAR, 2021)
        cDate.set(Calendar.MONTH, 12)
        cDate.set(Calendar.DAY_OF_MONTH, 3)
        assertFalse(cDate.notGreaterThanDate(cMustBeGreaterDate))
    }

    @Test
    fun testNotGreaterThanDate_greaterYear() {
        cDate.set(Calendar.YEAR, 2023)
        cDate.set(Calendar.MONTH, 11)
        cDate.set(Calendar.DAY_OF_MONTH, 3)
        assertFalse(cDate.notGreaterThanDate(cMustBeGreaterDate))
    }
}
