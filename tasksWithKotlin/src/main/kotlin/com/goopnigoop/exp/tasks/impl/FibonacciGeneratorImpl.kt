package com.goopnigoop.exp.tasks.impl

import com.goopnigoop.exp.tasks.FibonacciGenerator

class FibonacciGeneratorImpl : FibonacciGenerator {
    override fun generateWithRecursion(quantity: Int): List<Long> {
        fun getNrecursionNumber(number: Long): Long {
            return when (number) {
                1L -> 0
                2L -> 1
                else -> getNrecursionNumber(number - 1) + getNrecursionNumber(number - 2)
            }
        }
        return generateSequence(1L) { it + 1 }
            .map { getNrecursionNumber(it) }
            .take(quantity)
            .toList()
    }

    override fun generateWithRecursionWithCache(quantity: Int): List<Long> {
        val cache = mutableMapOf<Long, Long>()

        fun getNrecursionNumberWithCache(number: Long): Long {
            val currentValue = cache[number]
            return if (currentValue != null) currentValue else {
                if (number == 1L) {
                    return 0
                }
                if (number == 2L) {
                    return 1
                }
                cache[number] = getNrecursionNumberWithCache(number - 1) + getNrecursionNumberWithCache(number - 2)
                return cache[number]!!
            }

        }

        return generateSequence(1L) { it + 1 }
            .map {
                getNrecursionNumberWithCache(it)
            }
            .take(quantity)
            .toList()
    }

    override fun generateWithDeclarative(quantity: Int): List<Long> {
        return generateSequence(Pair(0L, 1L)) { Pair(it.second, it.first + it.second) }
            .map { it.first }
            .take(quantity)
            .toList()
    }

    override fun generate(quantity: Int): List<Long> {
        return when {
            quantity == 1 -> listOf(0)
            quantity == 2 -> listOf(0, 1)
            quantity > 2 -> {
                val array = LongArray(quantity)
                var firstSeed = 0L
                var secondSeed = 1L
                array[0] = firstSeed
                array[1] = secondSeed
                for (i in 2 until quantity) {
                    array[i] = firstSeed + secondSeed
                    firstSeed = secondSeed
                    secondSeed = array[i]
                }
                return array.toList()
            }
            else -> emptyList()
        }

    }
}