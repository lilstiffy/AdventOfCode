import java.lang.NumberFormatException

fun SolutionMenu(
    title: String,
    onP1Selected: (useTestData: Boolean) -> Unit,
    onP2Selected: (useTestData: Boolean) -> Unit
) {
    println("\uD83C\uDF84\uD83C\uDF84Advent of Code\uD83C\uDF84\uD83C\uDF84")
    println("\uD83C\uDF84\uD83C\uDF84Welcome to $title\uD83C\uDF84\uD83C\uDF84")
    println("Choose solution\n1: Solution 1\n2: Solution 2\n3: Solution 1 (TEST)\n4: Solution 2 (TEST)")
    print("Choose: ")

    try {
        when(readln().toInt()) {
            1 -> onP1Selected(false)
            2 -> onP2Selected(false)
            3 -> onP1Selected(true)
            4 -> onP2Selected(true)
            else -> println("Man you can't choose anything else")
        }
    } catch (e: NumberFormatException) {
        throw e
        println("Man you can't choose anything other than 1, 2, 3, 4 for real stop trying")
    }

}