package aoc2023.day3

import SolutionMenu
import aoc2022.day4.solution2
import extensions.aocToList
import readFileLines
import java.util.stream.Stream

fun main() {
    val data = readFileLines(path = "src/main/kotlin/aoc2023/day3/data.txt", relative = true)
    val testData = readFileLines(path = "src/main/kotlin/aoc2023/day3/test-data.txt", relative = true)
    SolutionMenu(
        title = "2023 Day 3",
        solutionOneSelected = { test -> if (test) solution1(testData) else solution1(data) },
        solutionTwoSelected = { test -> if (test) solution2(testData) else solution2(data) }
    )
}
private fun solution1(data: Stream<String>) {
    var partSum = 0
    var currentLineIdx = 0
    val dataList = data.aocToList()

    fun onNumberFinishedHandling(start: Int, end: Int, charBuffer: String) {
        val validPtNr = anySymbolAdjacent(
            forIndices = Pair(start, end),
            currentLine = currentLineIdx,
            dataList
        )
        if (validPtNr) {
            partSum += charBuffer.toInt()
            println("adding to ${charBuffer.toInt()} to partSum")
        }
    }
    
    dataList.forEach { line ->
        var start: Int? = null
        var end: Int?
        var charBuffer = ""
        line.forEachIndexed { idx, c -> 
            if (c.isDigit()) {
                if (start == null)
                    start = idx

                charBuffer += c
            } else {
                if (start != null) {
                    end = idx
                    onNumberFinishedHandling(start!!, end!!, charBuffer)
                    start = null
                    end = null
                    charBuffer = ""
                }
            }
        }
        start?.let {
            // In case number ends on last index of line
            onNumberFinishedHandling(it, line.lastIndex, charBuffer)
            start = null
            end = null
            charBuffer = ""
        }
        currentLineIdx++
    }
    println("the partsum is -> $partSum")
}

private fun Char.isSpecialCharacter(): Boolean {
    return (!this.isDigit() && this != '.')
}
private fun anySymbolAdjacent(
    forIndices: Pair<Int, Int>,
    currentLine: Int,
    data: List<String>
): Boolean {
    val isFirstLine = currentLine == 0
    val isLastLine = currentLine == data.lastIndex

    var symbolAdjacent = false

    val safeRangeStart = if (forIndices.first == 0) 0 else forIndices.first - 1
    val safeRangeEnd = if (forIndices.second == data[currentLine].lastIndex) data[currentLine].lastIndex else  forIndices.second + 1

    if (!isFirstLine)
        if (data[currentLine - 1].subSequence(safeRangeStart, safeRangeEnd).any { it.isSpecialCharacter()} )
            symbolAdjacent = true

    if (!isLastLine)
        if (data[currentLine + 1].subSequence(safeRangeStart, safeRangeEnd).any { it.isSpecialCharacter()} )
            symbolAdjacent = true

    if (data[currentLine].subSequence(safeRangeStart, safeRangeEnd).any { it.isSpecialCharacter()} )
        symbolAdjacent = true


    return symbolAdjacent
}

private fun solution2(data: Stream<String>) {

}