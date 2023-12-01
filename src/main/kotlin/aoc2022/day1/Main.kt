package aoc2022.day1

import readFileLines
import java.util.stream.Stream

fun main() {
    val pathToData = "/Users/stefanconsid/IdeaProjects/AdventOfCode-Day1/src/main/kotlin/aoc2022/day1/data.txt"

    val dataSet = readFileLines(pathToData)

    // P1
    //println("P1: ${getLargestCalorieNumber(dataSet)}")

    // P2
    println("P2: ${getTopThreeCalorieSum(dataSet)}")
}

fun getLargestCalorieNumber(dataSet: Stream<String>): Int {
    var greatestNr = 0
    var tempSum = 0

    dataSet.forEachOrdered { value ->
        if (value.isNotEmpty())
            tempSum += value.toInt()
        else {
            // New elf
            if (tempSum > greatestNr)
                greatestNr = tempSum

            tempSum = 0
        }
    }

    return greatestNr
}

fun getTopThreeCalorieSum(dataSet: Stream<String>): Int {
    val sums = arrayListOf<Int>()
    var tempSum = 0

    dataSet.forEachOrdered { value ->
        if (value.isNotEmpty())
            tempSum += value.toInt()
        else {
            sums.add(tempSum)
            tempSum = 0
        }
    }

    sums.sortByDescending { it }
    return sums[0] + sums[1] + sums[2]
}

