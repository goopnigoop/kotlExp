package com.goopnigoop.exp.tasks

interface FibonacciGenerator {
    fun generateWithRecursion(quantity: Int): List<Long>
    fun generateWithDeclarative(quantity: Int): List<Long>
    fun generate(quantity: Int): List<Long>
    fun generateWithRecursionWithCache(quantity: Int): List<Long>
}