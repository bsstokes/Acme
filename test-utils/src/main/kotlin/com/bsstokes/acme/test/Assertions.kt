package com.bsstokes.acme.test

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat

infix fun <T> T.shouldEqual(expected: T) = assertThat(this, equalTo(expected))
