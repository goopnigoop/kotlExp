package com.goopnigoop.exp.algorithms

fun bubbleSort(arr: IntArray): IntArray {
    for (i in arr.indices) {
        var isSwapped = false
        for (j in 0 until arr.size - 1) {
            if (arr[j] > arr[j + 1]) {
                swap(arr, j, j + 1)
                isSwapped = true
            }
        }
        if (!isSwapped) {
            break
        }
    }
    return arr
}

fun nextSort(arr: IntArray): IntArray {
    for (i in arr.indices) {
        for (j in i + 1 until arr.size) {
            if (arr[i] > arr[j]) {
                swap(arr, i, j)
            }
        }
    }
    return arr
}

fun insertionSort(arr: IntArray): IntArray {
    for (i in 1 until arr.size) {
        for (j in i downTo 1) {
            if (arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1)
            }
        }
    }
    return arr
}

fun quickSort(arr: IntArray): IntArray {
    fun qs(i: Int, j: Int): IntArray {
        var l = i
        var h = j
        val m = l + (h - l) / 2
        val med = arr[m]
        while (l <= h) {
            while (arr[l] < med) {
                l++
            }
            while (arr[h] > med) {
                h--
            }
            if (l <= h) {
                swap(arr, l, h)
                l++
                h--
            }
            if (l < j) {
                qs(l, j)
            }
            if (i < h) {
                qs(i, h)
            }
        }
        return arr
    }
    return qs(0, arr.size - 1)
}

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}