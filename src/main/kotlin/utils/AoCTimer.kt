package utils

import java.util.*

fun timeBlock(
    block: () -> Unit,
    result: (timeMillis: Long) -> Unit
) {
    val start = Date()
    block()
    val end = Date()
    result(end.time - start.time)
}