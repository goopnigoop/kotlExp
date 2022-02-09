package com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl

import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.Filter

class DoubleLettersFilter : Filter {
    override fun doesStringMatches(currentString: String): Boolean {
        for (index in 0 until currentString.length - 1) {
            if (currentString[index] == currentString[index + 1]) {
                return true
            }
        }
        return false
    }
}