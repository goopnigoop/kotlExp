package com.goopnigoop.adventschanllenges.fifteen.fifth.validators

class DoublePairFilter : Filter {
    override fun doesStringMatches(currentString: String): Boolean {
        for (i in 0 until currentString.length - 1) {
            val pair = String(charArrayOf(currentString[i], currentString[i + 1]))
            if (currentString.drop(i + 2).contains(pair)) {
                return true
            }
        }
        return false
    }
}
