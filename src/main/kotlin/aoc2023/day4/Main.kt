package aoc2023.day4

import utils.SolutionMenu
import extensions.aocToList
import utils.readFileLines
import java.util.stream.Stream
import kotlin.math.pow

fun main() {
    val data = readFileLines(path = "src/main/kotlin/aoc2023/day4/data.txt", relative = true)
    val testData = readFileLines(path = "src/main/kotlin/aoc2023/day4/test-data.txt", relative = true)
    SolutionMenu(
        title = "2023 Day 4",
        solutionOneSelected = { test -> if (test) solution1(testData) else solution1(data) },
        solutionTwoSelected = { test -> if (test) solution2(testData) else solution2(data) }
    )
}

data class ScratchCard(
    val data: Pair<List<Int>, List<Int>>
) {
    fun calculatePoints(): Int {
        var matches = 0
        data.first.forEach { nr ->
            matches += data.second.count { nr == it }
        }

        return if (matches == 0)
            0
        else
            2.0.pow(matches - 1).toInt()
    }

    fun calculateMatches(): Int {
        var matches = 0
        data.first.forEach { nr ->
            matches += data.second.count { nr == it }
        }
        return matches
    }
}
private fun solution1(data: Stream<String>) {
    var pointSum = 0
    data.forEachOrdered { line ->
        val cleanedLine = line.replaceFirst(Regex("^Card \\d+: "), "").trim()
        val games = cleanedLine.split(" | ")
        val firstPart = games[0].split(' ').filter { it.isNotEmpty() }.map { it.toInt() }
        val secondPart = games[1].split(' ').filter { it.isNotEmpty() }.map { it.toInt() }

        val scratchCard = ScratchCard(
            data = Pair(firstPart, secondPart)
        )

        pointSum += scratchCard.calculatePoints()
    }
    println(pointSum)
}

/**
 * Holy fuck this solution takes time LUL
 */
private fun solution2(data: Stream<String>) {
    var scratchCards = 0
    val allData = data.aocToList()

    fun processLine(idx: Int, line: String) {
        fun processScratchCard(lineIdx: Int, card: ScratchCard) {
            val matches = card.calculateMatches()
            if (matches > 0) {
                val processNextRange = lineIdx + 1..lineIdx + matches
                processNextRange.forEach { idxToProcess ->
                    processLine(idxToProcess, allData[idxToProcess])
                }
            }
        }

        val cleanedLine = line.replaceFirst(Regex("^Card \\d+: "), "").trim()
        val games = cleanedLine.split(" | ")
        val firstPart = games[0].split(' ').filter { it.isNotEmpty() }.map { it.toInt() }
        val secondPart = games[1].split(' ').filter { it.isNotEmpty() }.map { it.toInt() }

        val scratchCard = ScratchCard(
            data = Pair(firstPart, secondPart)
        )
        scratchCards++
        if (scratchCards % 100000 == 0)
            println(scratchCards)

        processScratchCard(idx, scratchCard)
    }

    allData.forEachIndexed { idx, line ->
        processLine(idx, line)
    }

    println("Total $scratchCards")
}