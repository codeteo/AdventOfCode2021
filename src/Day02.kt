import Action.*

fun main() {
    part1()
}

private fun part1() {
    val lines: List<String> = readInput("inputs/Day02_input")

    val position = Position(0, 0)

    for (line in lines) {
        val value = line.retrieveValue()
        when (line.toAction()) {
            DOWN -> position.depth += value
            FORWARD -> position.horizontal += value
            UP -> position.depth -= value
        }
    }

    println("Result is ${position.multiply}")
}

data class Position(var horizontal: Int, var depth: Int)

val Position.multiply: Int
    get() = horizontal * depth

sealed class Action {
    object UP : Action()
    object DOWN : Action()
    object FORWARD : Action()
}

fun String.toAction() = when {
    startsWith("fo") -> FORWARD
    startsWith("up") -> UP
    startsWith("do") -> DOWN
    else -> throw Exception("Not possible or wrong input")
}

fun String.retrieveValue(): Int = this.last().digitToIntOrNull() ?: 0