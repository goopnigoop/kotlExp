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

fun swap(arr: IntArray, i: Int, j: Int) {
    val temp = arr[i]
    arr[i] = arr[j]
    arr[j] = temp
}