package com.goopnigoop.exp.algorithms

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class SortKtTest {

    private val arr = intArrayOf(7, 1, 5, 8, 2, 34, 8, 43, 54, 3, 1, 23, 32, 2, 234, 235612, 1)

    @Test
    fun bubbleSort() {
        val sortedArr = bubbleSort(arr)
        assertEquals(arr.sorted(), sortedArr.toList())
    }

    @Test
    fun nextSortTest() {
        val sortedArr = nextSort(arr)
        assertEquals(arr.sorted(), sortedArr.toList())
    }

    @Test
    fun insertionSortTest() {
        val sortedArr = insertionSort(arr)
        assertEquals(arr.sorted(), sortedArr.toList())
    }

}