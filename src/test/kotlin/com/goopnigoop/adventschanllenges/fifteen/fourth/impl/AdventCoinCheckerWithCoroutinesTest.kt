package com.goopnigoop.adventschanllenges.fifteen.fourth.impl

import com.goopnigoop.adventschanllenges.fifteen.fourth.AdventCoinCheckerBaseTest
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.Executors

internal class AdventCoinCheckerWithCoroutinesTest : AdventCoinCheckerBaseTest() {

    private val dispatcher = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        dispatcher.close()
    }

    @Test
    fun checkMinNumberForInputHashWith5ZeroesWithCoroutines() {
        val list = CopyOnWriteArrayList<Int>()

        runBlocking(Dispatchers.Default) {
            launchChecks(list)
        }
        println("test is finished")
        list.filter { it == minimalNumber }.size shouldBe numberOfTimesServiceInvoked
    }

    private suspend fun launchChecks(list: MutableList<Int>) = coroutineScope {
        for (i in 1..numberOfTimesServiceInvoked) {
            launch {
                println("invocation started for ${Thread.currentThread().name}")

                withContext(Dispatchers.Default) {
                    val findFirstOccuranceWithZeros = checker.findFirstOccuranceWithZeros(inputString, zeroesQuantity)
                    list.add(findFirstOccuranceWithZeros)
                }
                println("invocation stopped for ${Thread.currentThread().name}")
            }
        }
    }
}