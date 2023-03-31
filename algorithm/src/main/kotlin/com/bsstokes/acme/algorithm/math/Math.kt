package com.bsstokes.acme.algorithm.math

internal val Int.isEven: Boolean get() = (this % 2 == 0)
internal val Int.isOdd: Boolean get() = !isEven
