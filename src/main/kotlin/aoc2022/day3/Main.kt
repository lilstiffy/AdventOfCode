package aoc2022.day3

import readFileLines
import java.util.stream.Stream

val items: List<Char> = ('a'..'z').toList()

typealias Item = Char
typealias Compartments = Pair<String, String>
fun main() {
    val data = readFileLines("/Users/stefanconsid/IdeaProjects/AdventOfCode-Day1/src/main/kotlin/aoc2022/day3/data.txt")
    // P1
    println("Sum of priority points is: ${solutionPart1(data)}")
}

fun solutionPart1(dataSet: Stream<String>): Int {
    var prioritySum = 0

    dataSet.forEachOrdered { ruckSack ->
        var match: Item? = null
        val compartments = ruckSack.splitInMiddle()
        compartments.first.forEach { c ->
            if (compartments.second.any { it == c }) {
                match = c
            }
        }
        match?.let { unwrappedMatch ->
            println("Match '$unwrappedMatch'")
            prioritySum += getPointsForItem(unwrappedMatch)
        }

    }
    return prioritySum
}

fun getPointsForItem(item: Item): Int {
    var offset = 0
    if (item.isUpperCase())
        offset = 26

    val score = items.indexOf(item.lowercaseChar()) + 1

    return score + offset
}

fun String.splitInMiddle(): Compartments {
    return Compartments(
        first = this.substring(0, this.length / 2),
        second = this.substring(this.length / 2, this.length)
    )
}