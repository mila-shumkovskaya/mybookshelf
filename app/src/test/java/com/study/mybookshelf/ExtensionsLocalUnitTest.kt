package com.study.mybookshelf

import com.study.mybookshelf.utils.*
import org.junit.Test
import org.junit.Assert.*

class ExtensionsLocalUnitTest {
    @Test
    fun testStringIsValidShortNotEmpty() {
        var string = "jhsvrflji68t puh7ft @##%$#^79I8*()3<?/" // length = 38, OK
        assertTrue(string.isValidShortNotEmpty())

        string = "j".repeat(SHORT_STRING_MAX_LENGTH)
        assertTrue(string.isValidShortNotEmpty()) // OK

        string = ""
        assertFalse(string.isValidShortNotEmpty()) // empty, not OK

        string = "l".repeat(SHORT_STRING_MAX_LENGTH + 1)
        assertFalse(string.isValidShortNotEmpty()) // not OK
    }

    @Test
    fun testStringIsValidShort() {
        var string = "jhsvrflji68t puh7ft @##%$#^79I8*()3<?/" // length = 38, OK
        assertTrue(string.isValidShort())

        string = "t".repeat(SHORT_STRING_MAX_LENGTH)
        assertTrue(string.isValidShort()) // OK

        string = ""
        assertTrue(string.isValidShort()) // empty, OK

        string = "s".repeat(SHORT_STRING_MAX_LENGTH + 1)
        assertFalse(string.isValidShort()) // not OK
    }

    @Test
    fun testStringIsValidLong() {
        var string = ""
        assertTrue(string.isValidLong()) // empty, OK

        string = "jhsvrflji68t puh7ft @##%$#^79I8*()3<?/" //length = 38, OK
        assertTrue(string.isValidLong())

        string = "a".repeat(SHORT_STRING_MAX_LENGTH)
        assertTrue(string.isValidLong()) // OK

        string = "h".repeat(LONG_STRING_MAX_LENGTH)
        assertTrue(string.isValidLong()) // OK

        string = "p".repeat(LONG_STRING_MAX_LENGTH + 1)
        assertFalse(string.isValidLong()) // not OK
    }
}