package com.bsstokes.acme.algorithm.words

internal val String.numberOfConsonants: Int get() = count { it.isConsonant }
internal val String.numberOfVowels: Int get() = count { it.isVowel }

private val Char.isConsonant: Boolean get() = AlphabetRules.CONSONANTS.contains(uppercaseChar())
private val Char.isVowel: Boolean get() = AlphabetRules.VOWELS.contains(uppercaseChar())

@Suppress("SpellCheckingInspection")
private object AlphabetRules {
    const val CONSONANTS = "BCDFGHJKLMNPQRSTVWXYZ"
    const val VOWELS = "AEIOU"
}
