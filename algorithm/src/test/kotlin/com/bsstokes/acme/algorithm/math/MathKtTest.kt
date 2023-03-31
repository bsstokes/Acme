package com.bsstokes.acme.algorithm.math

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
}
