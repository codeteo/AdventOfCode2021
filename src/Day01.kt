fun main() {
    val stringLines: List<String> = readInput("inputs/Day01_input")
    var lines: List<Int> = stringLines.map { it.toInt() }

    var prevLine = lines[0] // default is first
    var totalIncreased = 0
    for (line in lines.drop(1)) {
        if (line > prevLine) {
            totalIncreased++
        }
        prevLine = line
    }

    println("Result = $totalIncreased")
}
