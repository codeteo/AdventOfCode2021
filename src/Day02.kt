import Action.*

fun main() {
    part1()
    part2()
}

private fun part2() {
    val lines: List<String> = readInput("inputs/Day02_input")

    val position = PositionWithAim(0, 0, 0)

    for (line in lines) {
        val value = line.retrieveValue()
        when (line.toAction()) {
            DOWN -> {
                position.aim += value
            }
            UP -> {
                position.aim -= value
            }
            FORWARD -> {
                position.horizontal += value
                position.depth += position.aim * value
            }
        }
    }

    println("Result for Part 2 is: ${position.horizontal * position.depth}")
}

data class PositionWithAim(var horizontal: Int, var depth: Int, var aim: Int)

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

    println("Result for Part 1 is: ${position.multiply}")
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