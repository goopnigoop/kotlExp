package com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl

import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.Filter

class ThreeVowelsLettersFiler(private val requiredQuantityOfVowels: Int = 3) : Filter {

    private val vowels = setOf('a', 'e', 'i', 'o', 'u')

    override fun doesStringMatches(currentString: String): Boolean {
        return currentString.filter { letter -> letter in vowels }.toList().size >= requiredQuantityOfVowels
    }
}