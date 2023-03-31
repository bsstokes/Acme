package com.bsstokes.acme.algorithm.words

import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class WordsKtTest {

    @Test
    fun `vowels test`() {
        "".numberOfVowels shouldEqual 0
        "B".numberOfVowels shouldEqual 0
        "A".numberOfVowels shouldEqual 1
        "ABBA".numberOfVowels shouldEqual 2
        "How many?".numberOfVowels shouldEqual 2
        "The quick brown fox jumps over the lazy dog".numberOfVowels shouldEqual 11
    }

    @Test
    fun `consonants test`() {
        "".numberOfConsonants shouldEqual 0
        "B".numberOfConsonants shouldEqual 1
        "A".numberOfConsonants shouldEqual 0
        "ABBA".numberOfConsonants shouldEqual 2
        "How many?".numberOfConsonants shouldEqual 5
        "The quick brown fox jumps over the lazy dog".numberOfConsonants shouldEqual 24
    }
}
