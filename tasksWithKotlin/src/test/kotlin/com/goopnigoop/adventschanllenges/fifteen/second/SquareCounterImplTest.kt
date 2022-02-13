package com.goopnigoop.adventschanllenges.fifteen.second

import org.junit.Test
import java.io.File
import kotlin.test.assertEquals

private const val filePath = "src/test/kotlin/com/goopnigoop/adventschanllenges/fifteen/second/sample"

internal class SquareCounterImplTest {

    private val counter: PresentsDimensionsCounterImpl = PresentsDimensionsCounterImpl()

    @Test
    fun countSquare() {
        val lines = File(filePath).useLines { it.toList() }
        val countSquare = counter.countSquare(lines)
        assertEquals(1586300, countSquare)
    }

    @Test
    fun countPerimeter() {
        val lines = File(filePath).useLines { it.toList() }
        val countSquare = counter.countPerimeter(lines)
        assertEquals(3737498, countSquare)
    }
}