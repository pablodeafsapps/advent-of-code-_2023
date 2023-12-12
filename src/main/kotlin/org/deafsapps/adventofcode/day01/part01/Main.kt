package org.deafsapps.adventofcode.day01.part01

import java.io.File
import java.util.Scanner

fun main() {
    val input = Scanner(File("src/main/resources/day01-data.txt"))

    val digitsList = mutableListOf<Int>()
    while (input.hasNext()) {
        val twoDigitNumber = concatFirstAndLastDigit(line = input.nextLine()) ?: continue
        digitsList.add(twoDigitNumber)
    }
    val sum = digitsList.reduce { acc, next -> acc + next }
    println(sum)
}

fun concatFirstAndLastDigit(line: String): Int? {
    val firstDigit = line.find { it.isDigit() }?.toString()
    val lastDigit = line.reversed().find { it.isDigit() }?.toString()
    return (firstDigit + lastDigit).toIntOrNull()
}
