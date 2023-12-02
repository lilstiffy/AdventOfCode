import java.lang.NumberFormatException

fun SolutionMenu(
    title: String,
    onP1Selected: () -> Unit,
    onP2Selected: () -> Unit
) {
    println("\uD83C\uDF84\uD83C\uDF84Advent of Code\uD83C\uDF84\uD83C\uDF84")
    println("\uD83C\uDF84\uD83C\uDF84Welcome to $title\uD83C\uDF84\uD83C\uDF84")
    print("Choose solution (1) / (2): ")

    try {
        when(readln().toInt()) {
            1 -> onP1Selected()
            2 -> onP2Selected()
            else -> println("Man you can't choose anything else")
        }
    } catch (e: NumberFormatException) {
        println("Man you can't choose anything other than 1 or 2 for real stop trying")
    }

}