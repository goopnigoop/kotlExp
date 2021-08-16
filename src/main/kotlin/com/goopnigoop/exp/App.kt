package com.goopnigoop.exp

import java.util.stream.IntStream
import kotlin.streams.toList

fun main() {
    println(getNEven(10))
}

fun getNEven(number: Long) = IntStream.iterate(0) { it + 2 }.limit(number).toList()
