package com.bsstokes.acme.test

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue

infix fun <T> T.shouldEqual(expected: T) = assertThat(this, equalTo(expected))
infix fun <T> T.shouldNotEqual(expected: T) = assertThat(this, not(equalTo(expected)))

fun Boolean.assertFalse() = assertFalse(this)
fun Boolean.assertTrue() = assertTrue(this)

fun <T> T.assertNull() = assertNull(this)
fun <T> T.assertNotNull() = assertNotNull(this)

infix fun Any?.shouldBeSameInstanceAs(expected: Any?) = assertSame(expected, this)
