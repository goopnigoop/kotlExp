package com.goopnigoop.adventschanllenges.fifteen.fifth

import com.goopnigoop.adventschanllenges.fifteen.fifth.impl.NiceStringCheckerImpl
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.DoublePairFilter
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.RepeatLetterWithLetterBetweenFilter
import io.kotest.matchers.shouldBe
import org.junit.Test
import java.io.File

private const val filePath = "src/test/kotlin/com/goopnigoop/adventschanllenges/fifteen/fifth/sample"

internal class NiceStringCheckerTest {
    private val niceStringChecker = NiceStringCheckerImpl()

    @Test
    fun shouldFindProperNumberOfNiceStrings() {
        val lines = File(filePath).useLines { it.toList() }
        val numberOfNiceStrings = niceStringChecker.getNumberOfNiceStrings(lines)
        numberOfNiceStrings shouldBe 258
    }

    @Test
    fun shouldFindProperNumberOfNiceStringsForSecondTask() {
        val lines = File(filePath).useLines { it.toList() }
        val numberOfNiceStrings = niceStringChecker.getNumberOfNiceStringsNoCoroutines(
            lines, listOf(
                DoublePairFilter(),
                RepeatLetterWithLetterBetweenFilter()
            )
        )
        numberOfNiceStrings shouldBe 53
    }

    @Test
    fun shouldFindProperNumberOfNiceStringsNoCoroutines() {
        val lines = File(filePath).useLines { it.toList() }
        val numberOfNiceStrings = niceStringChecker.getNumberOfNiceStringsNoCoroutines(lines)
        numberOfNiceStrings shouldBe 258
    }
}