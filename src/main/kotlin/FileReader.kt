import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.stream.Stream


/**
 * @param path the path to the text file you want to read the lines for, can either be relative or absolute.
 * @param relative specify for the function if the path provided is a relative or absolute path
 * @param [Stream] of string holding each line of the read text file
 */
fun readFileLines(path: String, relative: Boolean = false): Stream<String> {
    return if (relative) {
        val file = File(path).absolutePath
        BufferedReader(FileReader(file)).lines()
    } else {
        BufferedReader(FileReader(path)).lines()
    }
}