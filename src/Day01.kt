fun main() {
    part1()
    part2()
}

private fun part2() {
    val stringLines: List<String> = readInput("inputs/Day01_input")
    val lines: List<Int> = stringLines.map { it.toInt() }

    var totalIncreased = 0
    for ((index, _) in lines.withIndex()) {
        if (index == lines.size - 3) break

        val first = lines[index] + lines[index + 1] + lines[index + 2]
        val second = lines[index + 1] + lines[index + 2] + lines[index + 3]

        if (second > first) {
            totalIncreased++
        }
    }
    println("Result Part 2 = $totalIncreased")
}

private fun part1() {
    val stringLines: List<String> = readInput("inputs/Day01_input")
    val lines: List<Int> = stringLines.map { it.toInt() }

    var prevLine = lines[0]
    var totalIncreased = 0
    for (line in lines.drop(1)) {
        if (line > prevLine) {
            totalIncreased++
        }
        prevLine = line
    }

    println("Result Part 1 = $totalIncreased")
}
