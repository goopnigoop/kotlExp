package com.goopnigoop.adventschanllenges.fifteen.first

class FloorChecker {
    fun getFloorNumber(inputLine: String): Int {
        val eachCount: Map<Char, Int> = inputLine.toCharArray().toList().groupingBy { it }.eachCount()
        return eachCount.getOrDefault('(', 0) - eachCount.getOrDefault(')', 0)
    }

    fun getPositionOfFirstBasementMove(inputLine: String): Int {
        var current = 0
        inputLine.toCharArray().asSequence().forEachIndexed { index, c ->
            when (c) {
                '(' -> current += 1
                else -> current -= 1
            }
            if (current < 0) {
                return index + 1
            }
        }
        return -1
    }
}