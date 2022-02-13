package com.goopnigoop.adventschanllenges.fifteen.third

import com.goopnigoop.adventschanllenges.fifteen.third.impl.HouseVisitorImpl

interface HouseVisitor {
    fun visit(inputMovements: String): Map<HouseVisitorImpl.Point, Int>
    fun visitBySeveralVisitors(inputMovements: String, quantityOfVisitors: Int): Map<HouseVisitorImpl.Point, Int>
    fun visitBySeveralVisitorsChunked(inputMovements: String, quantityOfVisitors: Int): Map<HouseVisitorImpl.Point, Int>
}