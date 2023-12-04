package aoc2023.day2

import SolutionMenu
import readFileLines
import java.util.stream.Stream

fun main() {
    val data = readFileLines(
        path = "src/main/kotlin/aoc2023/day2/data.txt",
        relative = true
    )

    SolutionMenu(
        title = "2023 Day 2",
        solutionOneSelected = { solutionPart1(data) },
        solutionTwoSelected = { solutionPart2(data) }
    )
}

fun solutionPart1(dataSet: Stream<String>) {
    var gameNumber = 1
    var gameNumberSum = 0

    dataSet.forEachOrdered { input ->
        val cleanedInput = input.replaceFirst(Regex("^Game \\d+: "), "").trim()
        var possible = true

        val gameTokens = cleanedInput.split(';').map { it.trim() }

        for (gameToken in gameTokens) {
            val colors = gameToken.split(',').map { it.trim() }

            for (color in colors) {
                val parts = color.split(' ')
                val cubeCount = parts[0].toInt()
                when (parts[1].lowercase()) {
                    "red" -> if (cubeCount > 12) possible = false
                    "green" -> if (cubeCount > 13) possible = false
                    "blue" -> if (cubeCount > 14) possible = false
                }
            }
        }

        if (possible) {
            println("✅Game id: $gameNumber is possible!✅")
            println()

            gameNumberSum += gameNumber
        } else {
            println("❌Game id: $gameNumber is not possible!❌")
            println()
        }

        gameNumber++
    }

    println("Sum of game Id's that are possible -> $gameNumberSum")
}

fun solutionPart2(dataSet: Stream<String>) {
    var gameNumber = 1
    var totalPowerSum = 0

    dataSet.forEachOrdered { input ->
        var reds = 0
        var greens = 0
        var blues = 0
        var gamesPowerSum = 0

        val cleanedInput = input.replaceFirst(Regex("^Game \\d+: "), "").trim()

        val gameTokens = cleanedInput.split(';').map { it.trim() }

        for (gameToken in gameTokens) {
            val colors = gameToken.split(',').map { it.trim() }

            for (color in colors) {
                val parts = color.split(' ')
                val cubeCount = parts[0].toInt()
                when (parts[1].lowercase()) {
                    "red" -> if (cubeCount > reds) reds = cubeCount
                    "green" -> if (cubeCount > greens) greens = cubeCount
                    "blue" -> if (cubeCount > blues) blues = cubeCount
                }
            }
        }

        gamesPowerSum = reds * greens * blues
        totalPowerSum += gamesPowerSum

        println("Game id: $gameNumber power sum is: $gamesPowerSum")


        gameNumber++
    }

    println("Total power sum is  -> $totalPowerSum")
}