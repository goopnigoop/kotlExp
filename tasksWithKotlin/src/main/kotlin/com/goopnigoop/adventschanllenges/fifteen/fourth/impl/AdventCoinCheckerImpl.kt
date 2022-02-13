package com.goopnigoop.adventschanllenges.fifteen.fourth.impl

import com.goopnigoop.adventschanllenges.fifteen.fourth.AdventCoinChecker
import java.security.MessageDigest
import kotlin.text.Charsets.UTF_8

class AdventCoinCheckerImpl : AdventCoinChecker {

    override fun findFirstOccuranceWithZeros(input: String, zeroNumbers: Int): Int {
        val zeros = "0".repeat(zeroNumbers)
        return generateSequence(1) { it + 1 }
            .filter { areAllZeros(it, input, zeros) }
            .first()
    }

    private fun areAllZeros(it: Int, input: String, zeros: String): Boolean {
        val md5 = MessageDigest.getInstance("MD5")
        return md5.digest((input + it).toByteArray(UTF_8)).toHex().startsWith(zeros)
    }

    private fun ByteArray.toHex(): String = joinToString(separator = "") { eachByte -> "%02x".format(eachByte) }
}