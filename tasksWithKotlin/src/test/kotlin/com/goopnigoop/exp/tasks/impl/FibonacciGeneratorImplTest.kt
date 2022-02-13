package com.goopnigoop.exp.tasks.impl

import io.kotest.matchers.collections.shouldContainExactly
import org.junit.Test

class FibonacciGeneratorImplTest {

    private val generator = FibonacciGeneratorImpl()
    private val firstTenFibonacci = arrayListOf(0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L)
    private val sizeOfLIst = firstTenFibonacci.size

    @Test
    fun shouldGenerateProperlyThirteenListOfFibonacciNumbersInWithRecursion() {
        val listOfFibNumbers = generator.generateWithRecursion(sizeOfLIst)
        listOfFibNumbers.shouldContainExactly(firstTenFibonacci)
    }
    @Test
    fun shouldGenerateProperlyThirteenListOfFibonacciNumbersInWithRecursionWithCache() {
        val listOfFibNumbers = generator.generateWithRecursionWithCache(sizeOfLIst)
        listOfFibNumbers.shouldContainExactly(firstTenFibonacci)
    }

    @Test
    fun shouldGenerateProperlyThirteenListOfFibonacciNumbersInDeclarativeWay() {
        val listOfFibNumbers = generator.generateWithDeclarative(sizeOfLIst)
        listOfFibNumbers.shouldContainExactly(firstTenFibonacci)
    }

    @Test
    fun shouldGenerateProperlyThirteenListOfFibonacciNumbers() {
        val listOfFibNumbers = generator.generate(sizeOfLIst)
        listOfFibNumbers.shouldContainExactly(firstTenFibonacci)
    }
}