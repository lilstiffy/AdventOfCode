package aoc2022.day4

import SolutionMenu
import readFileLines
import java.util.stream.Stream

fun main() {
    val data = readFileLines(
        path = "src/main/kotlin/aoc2022/day4/data.txt",
        relative = true
    )

    val testData = readFileLines(
        path = "src/main/kotlin/aoc2022/day4/test-data.txt",
        relative = true
    )

    SolutionMenu(
        title = "2022 Day 4",
        solutionOneSelected = { solution1(if (it) testData else data) },
        solutionTwoSelected = { solution2(if (it) testData else data) }
    )
}

typealias TaskRange = IntRange
private data class Elf(
    var taskRange: TaskRange = TaskRange(0,0)
) {
    constructor(raw: String) : this() {
        raw.split('-').let {
            taskRange = TaskRange(it[0].toInt(), it[1].toInt())
        }
    }
    fun taskRangeFullyContains(other: Elf): Boolean {
        return other.taskRange.first in taskRange && other.taskRange.last in taskRange
    }

    fun anyTasksOverlapping(other: Elf): Boolean {
        var conflict = false
        taskRange.forEach { task ->
            if (other.taskRange.contains(task))
                conflict = true
        }
        return conflict
    }
}
fun solution1(data: Stream<String>) {
    var countFullyContain = 0

    data.forEachOrdered { line ->
        val elves = line.split(',')
        val firstElf = Elf(elves.first())
        val lastElf = Elf(elves.last())

        if (firstElf.taskRangeFullyContains(lastElf) or lastElf.taskRangeFullyContains(firstElf))
            countFullyContain++

    }

    println(countFullyContain)
}

fun solution2(data: Stream<String>) {
    var conflictCount = 0
    data.forEachOrdered { line ->
        val elves = line.split(',')
        val firstElf = Elf(elves.first())
        val lastElf = Elf(elves.last())

        if (firstElf.anyTasksOverlapping(lastElf) or lastElf.anyTasksOverlapping(firstElf))
            conflictCount++

    }
    println(conflictCount)
}