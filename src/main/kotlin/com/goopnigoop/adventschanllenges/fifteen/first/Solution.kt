package com.goopnigoop.adventschanllenges.fifteen.first

fun main() {
    val inputLine = readLine()!!.trim()
    val floorNumber = getFloorNumber(inputLine)
    println("The Answer is : $floorNumber")
    val positionOfFirstBasementMove = getPositionOfFirstBasementMove(inputLine)
    println("The second answer is : $positionOfFirstBasementMove")

}

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
            return index+1
        }
    }
    return -1
}
