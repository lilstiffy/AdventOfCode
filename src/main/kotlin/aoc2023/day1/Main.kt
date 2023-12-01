package aoc2023.day1

import readFileLines


fun main() {
    var sum = 0
    val pathToData = "/Users/stefanconsid/IdeaProjects/AdventOfCode-Day1/src/main/kotlin/aoc2023/day1/aoc-day-1.txt"

    try {
        val data = readFileLines(pathToData)
        data.forEachOrdered { line ->
            sum += handleLine(line)
        }
        println("Calibration value is: $sum")
    } catch (e: Exception) {
        println("Yo man something went really wrong")
        println(e)
    }

}

fun handleLine(input: String): Int {
    val sanitizedLine = sanitizeLine(input)
    val charBuffer = charArrayOf(' ', ' ')
    var firstFound = false

    var lastKnownDigit = ' '

    sanitizedLine.forEach { c ->
        when {
            // Determine first
            c.isDigit() && !firstFound -> {
                charBuffer[0] = c
                firstFound = true
                lastKnownDigit = c
            }

            // Other cases yo
            c.isDigit() && firstFound -> {
                lastKnownDigit = c
            }
        }
    }

    charBuffer[1] = lastKnownDigit

    return String(charBuffer).toInt()
}

fun sanitizeLine(input: String): String {
    var output = input
    val wordMap = mapOf(
        "one" to "o1e",
        "two" to "t2o",
        "three" to "t3e",
        "four" to "f4r",
        "five" to "f5e",
        "six" to "s6",
        "seven" to "s7n",
        "eight" to "e8t",
        "nine" to "n9e"
    )

    wordMap.forEach { (key, value) ->
        output = output.replace(oldValue = key, newValue = value, ignoreCase = true)
    }

    return output
}