package extensions

import java.util.stream.Stream

/**
 * @return [List] of [String] from a [Stream] of [String]
 */
fun Stream<String>.aocToList(): List<String> {
    val mutableList = mutableListOf<String>()
    this.forEachOrdered {
        mutableList.add(it)
    }
    return mutableList
}