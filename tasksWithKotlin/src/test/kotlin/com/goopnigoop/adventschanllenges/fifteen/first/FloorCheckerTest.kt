package com.goopnigoop.adventschanllenges.fifteen.first

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Test

import org.junit.Assert.*
import java.io.File

private const val filePath = "src/test/kotlin/com/goopnigoop/adventschanllenges/fifteen/first/sample"

class FloorCheckerTest {

    private val floorChecker = FloorChecker()

    @Test
    fun testTheNumberOfFlorIsCountProperly() {
        val line = File(filePath).readLines().joinToString()
        val floorNumber = floorChecker.getFloorNumber(line)
        assertThat(floorNumber, equalTo(138))
    }

    @Test
    fun getPositionOfFirstBasementMove() {
        val line = File(filePath).readLines().joinToString()
        val floorNumber = floorChecker.getPositionOfFirstBasementMove(line)
        assertThat(floorNumber, equalTo(1771))
    }
}