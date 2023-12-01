package aoc2022.day2

import readFileLines
import java.util.stream.Stream

enum class Shape(val code: Char, val points: Int) {
    ROCK('A', 1),
    PAPER('B', 2),
    SCISSOR('C',3);

    fun getOutcomeAgainst(other: Shape): Outcome {
        return when (this) {
            ROCK -> when (other) {
                ROCK -> Outcome.TIE
                PAPER -> Outcome.LOSS
                SCISSOR -> Outcome.WIN
            }
            PAPER -> when (other) {
                ROCK -> Outcome.WIN
                PAPER -> Outcome.TIE
                SCISSOR -> Outcome.LOSS
            }
            SCISSOR -> when (other) {
                ROCK -> Outcome.LOSS
                PAPER -> Outcome.WIN
                SCISSOR -> Outcome.TIE
            }
        }
    }
}

enum class Outcome(val code: Char, val points: Int) {
    WIN('Z',6),
    TIE('Y',3),
    LOSS('X', 0);

    companion object {
        fun decodeOutcome(char: Char): Outcome {
            return when (char) {
                WIN.code -> WIN
                TIE.code -> TIE
                LOSS.code -> LOSS
                else -> throw Exception("Wtf")
            }
        }
        fun shapeNeededForOutcome(outcome: Outcome, opponentShape: Shape): Shape {
            return Shape.entries.find {
                it.getOutcomeAgainst(opponentShape) == outcome
            } ?: throw Exception("Something is super wrong")
        }
    }
}

fun main() {
    val data = readFileLines("/Users/stefanconsid/IdeaProjects/AdventOfCode-Day1/src/main/kotlin/aoc2022/day2/data.txt")

    // P1
    //println("Part1: ${solutionPart1(data)}")

    // P2
    println("Part2: ${solutionPart2(data)}")
}

fun solutionPart1(dataSet: Stream<String>): Int {
    fun handleLine(line: String): Int {
        val their = idToShape(line[0])
        val our = idToShape(line[2])

        if (their == null || our == null)
            return 0

        val outcome = our.getOutcomeAgainst(their)

        return outcome.points + our.points
    }

    var scoreSum = 0
    dataSet.forEachOrdered { line ->
        scoreSum += handleLine(line)
    }
    return scoreSum
}

fun idToShape(char: Char): Shape? {
    return if (char.isWhitespace())
        null
    else {
        when (char) {
            'A', 'X' -> Shape.ROCK
            'B', 'Y' -> Shape.PAPER
            'C', 'Z' -> Shape.SCISSOR
            else -> null
        }
    }
}

fun solutionPart2(dataSet: Stream<String>): Int {
    fun handleLine(line: String): Int {
        val their = idToShape(line[0])
        val desiredOutcome = Outcome.decodeOutcome(line[2])
        val ourShape = Outcome.shapeNeededForOutcome(desiredOutcome, their ?: throw Exception("Wtf"))

        val outcome = ourShape.getOutcomeAgainst(their)

        return outcome.points + ourShape.points
    }

    var scoreSum = 0

    dataSet.forEachOrdered { line ->
        scoreSum += handleLine(line)
    }
    return scoreSum
}