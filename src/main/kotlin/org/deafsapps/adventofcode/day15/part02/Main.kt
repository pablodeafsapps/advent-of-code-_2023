package org.deafsapps.adventofcode.day15.part02

import org.deafsapps.adventofcode.day15.part01.stringHash
import java.io.File
import java.util.HashMap
import java.util.Scanner

fun main() {
    val input = Scanner(File("src/main/resources/day15-data.txt"))

    val steps = input.nextLine().split(",").map { step -> getLensByStep(step = step) }

    val lensConfiguration = mutableMapOf<Int, MutableList<Lens>>()
    steps.forEach { step ->
        val index = stringHash(string = step.label)
        if (lensConfiguration[index]?.map { it.label }?.contains(step.label) == true) {
            if (step.focalLength != null) {
                lensConfiguration[index]!!.replaceAll { if (it.label == step.label) step else it }
            } else {
                lensConfiguration[index]!!.removeAt(lensConfiguration[index]!!.indexOfFirst { it.label == step.label })
            }
        } else {
            lensConfiguration[index] =
                ((lensConfiguration[index] ?: listOf()) + (step.takeIf { it.focalLength != null }?.let { listOf(it) }
                    ?: run { listOf() })).toMutableList()
        }
    }
    println(lensConfiguration.getFocusingPower())
}

private fun getLensByStep(step: String): Lens {
    val (label, focalLength) = step.split("""[=\-]""".toRegex())
    return Lens(label = label, focalLength = focalLength.toIntOrNull())
}

private fun Map<Int, MutableList<Lens>>.getFocusingPower(): Int {
    var result = 0
    entries.forEach { box ->
        result += box.getFocusingPower()
    }
    return result
}

private tailrec fun Map.Entry<Int, List<Lens>>.getFocusingPower(
    acc: Int = 0, slot: Int = 1, lensList: List<Lens> = component2()
): Int = if (lensList.isEmpty()) {
    acc
} else {
    val initial = lensList.first()
    val rest = lensList.drop(1)
    val partialAcc = acc + ((component1() + 1) * slot * (initial.focalLength ?: 1))
    getFocusingPower(acc = partialAcc, slot = slot + 1, lensList = rest)
}

private data class Lens(val label: String, val focalLength: Int?)
