package org.deafsapps.adventofcode.day04

import java.io.File
import java.util.Scanner

fun main() {
    val input = Scanner(File("src/main/resources/day04-data.txt"))

    val scratchCardList = mutableListOf<ScratchCard>()
    while (input.hasNext()) {
        val scratchCardLine = input.nextLine()
        val (winningNumbers, ownedNumbers) = scratchCardLine.getDataByRegex(regex = """Card.*: (.*)\|(.*)${'$'}""".toRegex())
        val scratchCard = ScratchCard(winningNumbers = winningNumbers, ownedNumbers = ownedNumbers)
        scratchCardList.add(scratchCard)
    }

    println(scratchCardList.getNumberOfMatches())
}

private fun String.getDataByRegex(regex: Regex): Pair<Set<Int>, Set<Int>> {
    val match = regex.find(this) ?: return emptySet<Int>() to emptySet()
    val first = match.groupValues[1].trim().split(""" +""".toRegex()).map { it.toInt() }.toSet()
    val second = match.groupValues[2].trim().split(""" +""".toRegex()).map { it.toInt() }.toSet()
    return first to second
}

private fun List<ScratchCard>.getNumberOfMatches(): Int {
    var sum = 0
    forEach { scratchCard ->
        var tmpAcc = 0
        scratchCard.ownedNumbers.forEach { ownedNumber ->
            if (scratchCard.winningNumbers.contains(ownedNumber)) {
                tmpAcc++
            }
        }
        sum += weightedSum(hits = tmpAcc)
    }
    return sum
}

private fun weightedSum(acc: Int = 0, hits: Int): Int =
    when (hits) {
        0 -> 0
        1 -> acc + 1
        else -> 2 * weightedSum(acc, hits - 1)
    }

private data class ScratchCard(val winningNumbers: Set<Int>, val ownedNumbers: Set<Int>)
