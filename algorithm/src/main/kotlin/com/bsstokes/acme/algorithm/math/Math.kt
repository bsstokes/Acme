package com.bsstokes.acme.algorithm.math

internal val Int.isEven: Boolean get() = (this % 2 == 0)
internal val Int.isOdd: Boolean get() = !isEven

internal fun Int.primeFactors(): Set<Int> {
    var number = this
    val primeFactors = mutableSetOf<Int>()

    var i = 2
    while (i <= number) {
        if (number % i == 0) {
            primeFactors.add(i)
            number /= i
            i--
        }

        i++
    }

    return primeFactors
}
