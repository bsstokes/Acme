package com.bsstokes.acme.algorithm.math

import com.bsstokes.acme.test.assertFalse
import com.bsstokes.acme.test.assertTrue
import com.bsstokes.acme.test.shouldEqual
import org.junit.Test

class MathKtTest {

    @Test
    fun `an even number isEven`() {
        0.isEven shouldEqual true
        (-2).isEven shouldEqual true
        1_024.isEven shouldEqual true

        0.isOdd shouldEqual false
        (-2).isOdd shouldEqual false
        1_024.isOdd shouldEqual false
    }

    @Test
    fun `an odd number isOdd`() {
        1.isOdd shouldEqual true
        (-1).isOdd shouldEqual true
        1_023.isOdd shouldEqual true

        1.isEven shouldEqual false
        (-1).isEven shouldEqual false
        1_023.isEven shouldEqual false
    }

    @Test
    fun `primeFactors() determines prime factors`() {
        0.primeFactors() shouldEqual emptySet()
        2.primeFactors() shouldEqual setOf(2)
        9.primeFactors() shouldEqual setOf(3)
        13.primeFactors() shouldEqual setOf(13)
        25.primeFactors() shouldEqual setOf(5)
        36.primeFactors() shouldEqual setOf(2, 3)
        210.primeFactors() shouldEqual setOf(2, 3, 5, 7)
        (210 * 8).primeFactors() shouldEqual setOf(2, 3, 5, 7)

        12.primeFactors() shouldEqual setOf(2, 3)
        18.primeFactors() shouldEqual setOf(2, 3)
        24.primeFactors() shouldEqual setOf(2, 3)
        20.primeFactors() shouldEqual setOf(2, 5)
        36.primeFactors() shouldEqual setOf(2, 3)
    }

    @Test
    fun `haveCommonPrimeFactors() determines if two numbers share prime factors`() {
        haveCommonPrimeFactors(2, 4).assertTrue()
        haveCommonPrimeFactors(4, 13).assertFalse()
        haveCommonPrimeFactors(15, 21).assertTrue()
        haveCommonPrimeFactors(15, 23).assertFalse()
    }
}
