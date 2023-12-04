package utils

import java.lang.NumberFormatException

fun SolutionMenu(
    title: String,
    solutionOneSelected: (useTestData: Boolean) -> Unit,
    solutionTwoSelected: (useTestData: Boolean) -> Unit,
) {
    println("\uD83C\uDF84\uD83C\uDF84Welcome to $title\uD83C\uDF84\uD83C\uDF84")
    println("Choose solution\n1: Solution 1\n2: Solution 2\n3: Solution 1 (TEST)\n4: Solution 2 (TEST)\n5: Time solution 1\n6: Time solution 2")
    print("Choose: ")

    val input = try {
        readln().toInt()
    } catch (e: NumberFormatException) {
        println("Man you can't choose anything other than 1, 2, 3, 4, 5, 6 for real stop trying")
        return
    }

    when(input) {
        1 -> solutionOneSelected(false)
        2 -> solutionTwoSelected(false)
        3 -> solutionOneSelected(true)
        4 -> solutionTwoSelected(true)
        5 -> {
            timeBlock(block = { solutionOneSelected(false) }) { timeElapsedMs ->
                println("Solution 2 took: ${timeElapsedMs}ms")
            }
        }
        6 -> {
            timeBlock(block = { solutionTwoSelected(false) }) { timeElapsedMs ->
                println("Solution 2 took: ${timeElapsedMs}ms")
            }
        }
        else -> println("Man you can't choose anything else")
    }
}