package org.deafsapps.adventofcode.day15.part01

import java.io.File
import java.util.Scanner

fun main() {
    val input = Scanner(File("src/main/resources/day15-data.txt"))

    val steps = input.nextLine().split(",")
    println(allStringsHash(strings = steps))
}

private tailrec fun allStringsHash(acc: Int = 0, strings: List<String>): Int =
    if (strings.isEmpty()) {
        acc
    } else {
        allStringsHash(acc = acc + stringHash(string = strings.first()), strings = strings.drop(1))
    }

tailrec fun stringHash(acc: Int = 0, string: String): Int =
    if (string == "") {
        acc
    } else {
        val initial = string.first()
        val rest = string.drop(1)
        val partialAcc = (acc + initial.code) * 17 % 256
        stringHash(acc = partialAcc, string = rest)
    }
