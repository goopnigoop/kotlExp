package com.goopnigoop.adventschanllenges.fifteen.third.impl

import com.goopnigoop.adventschanllenges.fifteen.third.HouseVisitor
import org.junit.jupiter.api.Test
import java.io.File
import kotlin.test.assertEquals

private const val filePath = "src/test/kotlin/com/goopnigoop/adventschanllenges/fifteen/third/impl/sample"

class HouseVisitorImplTest {
    private val houseVisitor: HouseVisitor = HouseVisitorImpl()

    @Test
    internal fun shouldCountAllVisitedHouses() {
        val content = File(filePath).readLines().joinToString()
        val mapOfHouses = houseVisitor.visit(content)
        assertEquals(2565, mapOfHouses.size)
    }
    @Test
    internal fun shouldCountAllVisitedHousesForMultipleVisitors() {
        val content = File(filePath).readLines().joinToString()
        val mapOfHouses = houseVisitor.visitBySeveralVisitors(content,2)
        assertEquals(2639, mapOfHouses.size)
    }
}