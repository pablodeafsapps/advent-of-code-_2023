package org.deafsapps.adventofcode.day01.part02

import org.deafsapps.adventofcode.day01.part01.concatFirstAndLastDigit
import java.io.File
import java.util.Scanner

fun main() {
    val input = Scanner(File("src/main/resources/day01-data.txt"))

    val digitsList = mutableListOf<Int>()
    while (input.hasNext()) {
        val updatedLine = input.nextLine().formatNonNumericDigits().parseDigits()
        val twoDigitNumber = concatFirstAndLastDigit(line = updatedLine) ?: continue
        digitsList.add(twoDigitNumber)
    }
    val sum = digitsList.reduce { acc, next -> acc + next }
    println(sum)
}

private fun String.formatNonNumericDigits(): String = lowercase().replace(regex = allDigitsRegex) { matchResult ->
    when (matchResult.value) {
        "zero" -> "zeroo"
        "one" -> "onee"
        "two" -> "twoo"
        "three" -> "threee"
        "four" -> "fourr"
        "five" -> "fivee"
        "six" -> "sixx"
        "seven" -> "sevenn"
        "eight" -> "eightt"
        "nine" -> "ninee"
        else -> ""
    }
}

private fun String.parseDigits(): String = lowercase().replace(regex = allDigitsRegex) { matchResult ->
    when (matchResult.value) {
        "zero" -> "0"
        "one" -> "1"
        "two" -> "2"
        "three" -> "3"
        "four" -> "4"
        "five" -> "5"
        "six" -> "6"
        "seven" -> "7"
        "eight" -> "8"
        "nine" -> "9"
        else -> ""
    }
}

private val allDigitsRegex: Regex =
    """zero|one|two|three|four|five|six|seven|eight|nine|""".toRegex()
