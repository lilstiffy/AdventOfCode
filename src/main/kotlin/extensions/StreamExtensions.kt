package extensions

import java.util.stream.Stream

fun Stream<String>.aocToList(): List<String> {
    val mutableList = mutableListOf<String>()
    this.forEachOrdered {
        mutableList.add(it)
    }
    return mutableList
}