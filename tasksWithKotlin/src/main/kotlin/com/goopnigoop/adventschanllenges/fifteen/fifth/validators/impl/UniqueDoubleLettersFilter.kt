package com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl

import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.Filter

class UniqueDoubleLettersFilter : Filter {

    private val stringsToAvoid = setOf("ab", "cd", "pq", "xy")

    override fun doesStringMatches(currentString: String): Boolean {
        return currentString.findAnyOf(stringsToAvoid) == null
    }
}