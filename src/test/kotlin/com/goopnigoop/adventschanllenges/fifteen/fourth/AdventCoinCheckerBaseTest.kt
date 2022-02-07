package com.goopnigoop.adventschanllenges.fifteen.fourth

import com.goopnigoop.adventschanllenges.fifteen.fourth.impl.AdventCoinCheckerImpl

open class AdventCoinCheckerBaseTest {

    protected val checker: AdventCoinChecker = AdventCoinCheckerImpl()
    protected val inputString = "bgvyzdsv"
    protected val threadsQuantity = 4
    protected val numberOfTimesServiceInvoked = 30
    protected val zeroesQuantity = 5
    protected val minimalNumber = 254575
}
