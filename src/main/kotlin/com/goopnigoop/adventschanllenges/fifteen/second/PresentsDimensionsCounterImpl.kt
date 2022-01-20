package com.goopnigoop.adventschanllenges.fifteen.second

import kotlin.streams.asSequence

class PresentsDimensionsCounterImpl : SquareCounter, PerimeterCounter {
    override fun countSquare(lines: List<String>): Long {
        return getDimensions(lines).map { getSquare(it).toLong() }.sum()
    }

    override fun countPerimeter(lines: List<String>): Long {
        return getDimensions(lines).map { getPerimeter(it) }.sum()
    }

    private fun getPerimeter(dimension: Dimension): Long {
        val (h, w, l) = dimension
        val arrayOfDimensions = arrayOf(h, w, l)
        arrayOfDimensions.sort()
        val perimeters = arrayOfDimensions[0] * 2 + arrayOfDimensions[1] * 2
        return perimeters + arrayOfDimensions.reduce { acc, i -> acc * i }.toLong()
    }

    private fun getSquare(dimension: Dimension): Int {
        val first = dimension.l * dimension.w
        val second = dimension.w * dimension.h
        val three = dimension.h * dimension.l
        val min = minOf(first, second, three)
        return 2 * (first + second + three) + min
    }

    private fun getDimension(it: String): Dimension {
        val (h, w, l) = it.split('x').map { param -> param.toInt() }
        return Dimension(h, w, l)
    }

    data class Dimension(val h: Int, val w: Int, val l: Int)


    private fun getDimensions(lines: List<String>) = lines.stream().asSequence().map { getDimension(it.trim()) }

}