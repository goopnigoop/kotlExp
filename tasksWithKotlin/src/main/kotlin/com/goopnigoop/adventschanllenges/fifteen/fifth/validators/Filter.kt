package com.goopnigoop.adventschanllenges.fifteen.fifth.validators

interface Filter {
    fun filter(list: List<String>) = list.filter { doesStringMatches(it) }.toList()

    fun doesStringMatches(currentString: String): Boolean
}