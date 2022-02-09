package com.goopnigoop.adventschanllenges.fifteen.fifth.validators

class RepeatLetterWithLetterBetweenFilter : Filter {
    override fun doesStringMatches(currentString: String): Boolean {
        for (index in 0 until currentString.length - 2) {
            if (currentString[index] == currentString[index + 2]) {
                return true
            }
        }
        return false
    }
}