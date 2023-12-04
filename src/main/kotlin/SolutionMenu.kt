import java.lang.NumberFormatException
import java.util.*

fun SolutionMenu(
    title: String,
    solutionOneSelected: (useTestData: Boolean) -> Unit,
    solutionTwoSelected: (useTestData: Boolean) -> Unit,
) {
    println("\uD83C\uDF84\uD83C\uDF84Welcome to $title\uD83C\uDF84\uD83C\uDF84")
    println("Choose solution\n1: Solution 1\n2: Solution 2\n3: Solution 1 (TEST)\n4: Solution 2 (TEST)\n5: Time solution 1\n6: Time solution 2")
    print("Choose: ")

    try {
        when(readln().toInt()) {
            1 -> solutionOneSelected(false)
            2 -> solutionTwoSelected(false)
            3 -> solutionOneSelected(true)
            4 -> solutionTwoSelected(true)
            5 -> {
                val start = Date()
                solutionOneSelected(false)
                val end = Date()
                val timeElapsedMs = end.time - start.time
                println("Solution 1 took: ${timeElapsedMs}ms")
            }
            6 -> {
                val start = Date()
                solutionTwoSelected(false)
                val end = Date()
                val timeElapsedMs = end.time - start.time
                println("Solution 2 took: ${timeElapsedMs}ms")
            }
            else -> println("Man you can't choose anything else")
        }
    } catch (e: NumberFormatException) {
        throw e
        println("Man you can't choose anything other than 1, 2, 3, 4, 5, 6 for real stop trying")
    }

}