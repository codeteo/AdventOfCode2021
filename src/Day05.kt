fun main() {
    part1()
}


private fun part1() {
    val input = readInput("inputs/Day05_test_input")

    val inputLines = mutableListOf<String>()
    input.forEach { line: String ->
        inputLines.add(line.replace(" -> ", "-"))
    }

    val lines = mutableListOf<Line>()
    inputLines.forEach { line ->
        val split = line.split("-")
        lines.add(Line(split[0].split(",").toPoint(), split[1].split(",").toPoint()))
    }

    // keep only  lines where either x1 = x2 or y1 = y2.
    val filteredLines: List<Line> = lines.filter { it.start.first == it.end.first || it.start.second == it.end.second }

    println(filteredLines)

    // find max and min X,Y
}

fun List<String>.toPoint() = Point(this[0].toInt(), this[1].toInt())

typealias Point = Pair<Int, Int>

data class Line(var start: Point, var end: Point)

// find max X and Y and min X andY