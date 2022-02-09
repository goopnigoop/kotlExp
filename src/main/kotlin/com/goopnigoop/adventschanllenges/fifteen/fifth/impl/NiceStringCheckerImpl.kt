package com.goopnigoop.adventschanllenges.fifteen.fifth.impl

import com.goopnigoop.adventschanllenges.fifteen.fifth.NiceStringChecker
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.Filter
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl.DoubleLettersFilter
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl.ThreeVowelsLettersFiler
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl.UniqueDoubleLettersFilter
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap

class NiceStringCheckerImpl(private val dispatcher: CoroutineDispatcher = Dispatchers.Default) : NiceStringChecker {

    override fun getNumberOfNiceStrings(lines: List<String>, filters: List<Filter>): Int = runBlocking(dispatcher)
    {
        val resultMap = ConcurrentHashMap<String, Int>()
        runFiltering(lines, resultMap, filters)
        resultMap.filter { it.value > filters.size - 1 }.size
    }


    private suspend fun runFiltering(
        lines: List<String>,
        resultMap: ConcurrentHashMap<String, Int>,
        filters: List<Filter>
    ) = coroutineScope {
        filters.forEach {
            launch {
                println("invocation started for ${Thread.currentThread().name}")
                withContext(dispatcher) {
                    it.filter(lines).forEach { resultMap.merge(it, 1) { first, second -> first + second } }
                }
                println("invocation stopped for ${Thread.currentThread().name}")
            }
        }
    }


    fun getNumberOfNiceStringsNoCoroutines(
        lines: List<String>, filters: List<Filter> = listOf(
            DoubleLettersFilter(), ThreeVowelsLettersFiler(), UniqueDoubleLettersFilter()
        )
    ): Int {
        var list = lines
        for (validator in filters) {
            list = validator.filter(list)
        }
        return list.size
    }

}
