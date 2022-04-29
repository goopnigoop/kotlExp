package com.goopnigoop.adventschanllenges.fifteen.fifth

import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.Filter
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl.DoubleLettersFilter
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl.ThreeVowelsLettersFiler
import com.goopnigoop.adventschanllenges.fifteen.fifth.validators.impl.UniqueDoubleLettersFilter

interface NiceStringChecker {
    fun getNumberOfNiceStrings(
        lines: List<String>, filters: List<Filter> = listOf(
            DoubleLettersFilter(), ThreeVowelsLettersFiler(), UniqueDoubleLettersFilter()
        )
    ): Int
}
