package com.study.mybookshelf

import com.study.mybookshelf.utils.*
import org.junit.Test
import org.junit.Assert.*

class ExtensionsStringIsValidUnitTest {
    @Test
    fun testStringIsValidShortNotEmpty_differentSymbols() {
        val string = "jhsvrflji68t puh7ft @##%$#^79I8*()3<?/" // length = 38, OK
        assertTrue(string.isValidShortNotEmpty())
    }
    @Test
    fun testStringIsValidShortNotEmpty_maxShortLength() {
        val string = "j".repeat(SHORT_STRING_MAX_LENGTH)
        assertTrue(string.isValidShortNotEmpty()) // OK
    }
    @Test
    fun testStringIsValidShortNotEmpty_empty() {
        val string = ""
        assertFalse(string.isValidShortNotEmpty()) // empty, not OK
    }
    @Test
    fun testStringIsValidShortNotEmpty_longerThanMaxShortLength() {
        val string = "l".repeat(SHORT_STRING_MAX_LENGTH + 1)
        assertFalse(string.isValidShortNotEmpty()) // not OK
    }

    @Test
    fun testStringIsValidShort_differentSymbols() {
        val string = "jhsvrflji68t puh7ft @##%$#^79I8*()3<?/" // length = 38, OK
        assertTrue(string.isValidShort())
    }
    @Test
    fun testStringIsValidShort_maxShortLength() {
        val string = "t".repeat(SHORT_STRING_MAX_LENGTH)
        assertTrue(string.isValidShort()) // OK
    }
    @Test
    fun testStringIsValidShort_empty() {
        val string = ""
        assertTrue(string.isValidShort()) // empty, OK
    }
    @Test
    fun testStringIsValidShort_longerThanMaxShortLength() {
        val string = "s".repeat(SHORT_STRING_MAX_LENGTH + 1)
        assertFalse(string.isValidShort()) // not OK
    }

    @Test
    fun testStringIsValidLong_empty() {
        val string = ""
        assertTrue(string.isValidLong()) // empty, OK
    }
    @Test
    fun testStringIsValidLong_differentSymbols() {
        val string = "jhsvrflji68t puh7ft @##%$#^79I8*()3<?/" //length = 38, OK
        assertTrue(string.isValidLong())
    }
    @Test
    fun testStringIsValidLong_maxShortLength() {
        val string = "a".repeat(SHORT_STRING_MAX_LENGTH)
        assertTrue(string.isValidLong()) // OK
    }
    @Test
    fun testStringIsValidLong_maxLongLength() {
        val string = "h".repeat(LONG_STRING_MAX_LENGTH)
        assertTrue(string.isValidLong()) // OK
    }
    @Test
    fun testStringIsValidLong_longerThanMaxLongLength() {
        val string = "p".repeat(LONG_STRING_MAX_LENGTH + 1)
        assertFalse(string.isValidLong()) // not OK
    }
}