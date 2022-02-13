package com.goopnigoop.adventschanllenges.fifteen.third.impl

import com.goopnigoop.adventschanllenges.fifteen.third.HouseVisitor
import java.util.function.Function
import java.util.stream.Stream

class HouseVisitorImpl : HouseVisitor {
    private val mapWithSigns =
        hashMapOf("^" to Function<Point, Point> { inputPoint: Point -> Point(inputPoint.x, inputPoint.y + 1) },
            "v" to Function<Point, Point> { inputPoint: Point -> Point(inputPoint.x, inputPoint.y - 1) },
            ">" to Function<Point, Point> { inputPoint: Point -> Point(inputPoint.x + 1, inputPoint.y) },
            "<" to Function<Point, Point> { inputPoint: Point -> Point(inputPoint.x - 1, inputPoint.y) })

    private var initialPoint = Point(0, 0)

    override fun visit(inputMovements: String): Map<Point, Int> {
        val mapOfHouses = mutableMapOf<Point, Int>()
        mapOfHouses[initialPoint] = 1

        var point: Point = initialPoint
        for (sign in inputMovements.split("")) {
            point = mapWithSigns.getOrDefault(sign, Function.identity()).apply(point)
            mapOfHouses.merge(point, 1) { new, old -> new + old }
        }
        return mapOfHouses
    }

    override fun visitBySeveralVisitors(inputMovements: String, quantityOfVisitors: Int): Map<Point, Int> {
        val listOfMaps = initializeListOfMaps(quantityOfVisitors)

        var counter = quantityOfVisitors

        val split = inputMovements.chunked(1)
        for (sign in split) {
            val currentIndex = counter++ % quantityOfVisitors
            val currentPair = listOfMaps[currentIndex]
            val currentPoint = mapWithSigns.getOrDefault(sign, Function.identity()).apply(currentPair.second)
            currentPair.first.merge(currentPoint, 1) { new, old -> new + old }
            listOfMaps[currentIndex] = Pair(currentPair.first, currentPoint.copy())
        }
        return listOfMaps.map { it.first }.reduce { map, nextMap -> (map + nextMap) as MutableMap<Point, Int> }
    }

    override fun visitBySeveralVisitorsChunked(inputMovements: String, quantityOfVisitors: Int): Map<Point, Int> {
        val chunked = inputMovements.chunked(quantityOfVisitors)
        var initial = mutableMapOf<Point, Int>()
        for (i in 0 until quantityOfVisitors) {
            val map = visit(chunked.map { it[i] }.joinToString())
            initial = (initial + map) as MutableMap<Point, Int>
        }
        return initial
    }


    private fun initializeListOfMaps(quantityOfVisitors: Int): MutableList<Pair<MutableMap<Point, Int>, Point>> {
        return Stream.generate { mutableMapOf<Point, Int>().also { it[initialPoint] = 1 } }
            .limit(quantityOfVisitors.toLong())
            .map { map -> Pair(map, initialPoint) }
            .toList().toMutableList()
    }

    data class Point(val x: Int, val y: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Point

            if (x != other.x) return false
            if (y != other.y) return false

            return true
        }

        override fun hashCode(): Int {
            var result = x
            result = 31 * result + y
            return result
        }

        fun copy(): Point = Point(this.x, this.y)
    }
}