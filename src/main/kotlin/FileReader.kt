import java.io.BufferedReader
import java.io.FileReader
import java.util.stream.Stream


fun readFileLines(path: String): Stream<String> {
    return BufferedReader(FileReader(path)).lines()
}