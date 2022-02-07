package com.goopnigoop.adventschanllenges.fifteen.fourth.impl

import com.goopnigoop.adventschanllenges.fifteen.fourth.AdventCoinCheckerBaseTest
import io.kotest.matchers.shouldBe
import org.junit.Test
import java.util.concurrent.Callable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.Future

internal class AdventCoinCheckerImplTest: AdventCoinCheckerBaseTest() {

    @Test
    fun checkMinNumberForInputHashWith5Zeroes() {
        val minNumber = checker.findFirstOccuranceWithZeros(inputString, zeroesQuantity)
        minNumber shouldBe minimalNumber
    }

    @Test
    fun checkMinNumberForInputHashWith5Zeroes4ThreadsIn() {

        val fixedThreadPool = Executors.newFixedThreadPool(threadsQuantity)
        val list: MutableList<Future<Int>> = fixedThreadPool.invokeAll((1..numberOfTimesServiceInvoked).map {
            Callable {
                println("invocation started for ${Thread.currentThread().name}")
                val findFirstOccuranceWithZeros = checker.findFirstOccuranceWithZeros(inputString, zeroesQuantity)
                println("invocation stopped for ${Thread.currentThread().name}")
                findFirstOccuranceWithZeros
            }
        })
        fixedThreadPool.shutdown()

        val resultLust = list.map { it.get() }.filter { it == 254575 }.toList()
        resultLust.size shouldBe numberOfTimesServiceInvoked
    }

    @Test
    fun checkMinNumberForInputHashWith5ZeroesWithCompletableFuture() {

        val newFixedThreadPool = Executors.newFixedThreadPool(threadsQuantity)
        val listOfFutures: List<CompletableFuture<Int>> = (1..numberOfTimesServiceInvoked).map {
            CompletableFuture.supplyAsync({
                println("started operation for ${Thread.currentThread().name}")
                val findFirstOccuranceWithZeros = checker.findFirstOccuranceWithZeros(inputString, zeroesQuantity)
                println("stopped operation for ${Thread.currentThread().name}")
                findFirstOccuranceWithZeros
            }, newFixedThreadPool)
        }.toList()
        val allOfFeatures: CompletableFuture<Void> = CompletableFuture.allOf(*listOfFutures.toTypedArray())
        val resultListCompletableFuture: CompletableFuture<List<Int>> =
            allOfFeatures.thenApply { listOfFutures.map { it.join() }.filter { it == 254575 }.toList() }

        resultListCompletableFuture.get().size shouldBe numberOfTimesServiceInvoked
    }

/*    @Test
    fun checkMinNumberForInputHashWith5ZeroesWithCoroutines() {
        val dispatcher = Executors
            .newSingleThreadExecutor()
            .asCoroutineDispatcher()

        Dispatchers.setMain(dispatcher)

        runBlocking(Dispatchers.Main) {
            val list = mutableListOf<Int>()
            launchChecks(list)
            assertEquals(100, list.filter { it == 254575 }.size)
        }

        Dispatchers.resetMain()
        dispatcher.close()

    }

    private suspend fun launchChecks(list: MutableList<Int>) = coroutineScope {

        for (i in 1..100) {
            launch {
                println("1 started operation for ${Thread.currentThread().name}")

                withContext(Dispatchers.Default) {
                    val findFirstOccuranceWithZeros = checker.findFirstOccuranceWithZeros("bgvyzdsv", 5)
                    list.add(findFirstOccuranceWithZeros)

                }

                println("1 stopped operation for ${Thread.currentThread().id}")


            }
        }




        println("end")
    }

    @Test
    fun main() = runBlocking {
        doWorld()
        println("Done")
    }

    // Concurrently executes both sections
    suspend fun doWorld() = coroutineScope { // this: CoroutineScope
        launch {
            println("started1")
            delay(2000L)
            println("World 2")
        }
        launch {
            println("started2")
            delay(1000L)
            println("World 1")
        }
        println("Hello")
    }*/
}