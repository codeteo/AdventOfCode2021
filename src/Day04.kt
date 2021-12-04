import java.io.File

fun main() {
    part1()
}

private fun part1() {
    val input = readInput("inputs/Day04_input")
    val allDrawnNumbers: List<Int> = input.firstOrNull()?.split(",")?.map { it.toIntOrNull() ?: 0 } ?: emptyList()

    val reader = File("src", "inputs/Day04_input.txt").bufferedReader().readLines()
    var matrix: Array<IntArray> = emptyArray()
    val allMatrixes = mutableListOf<Array<IntArray>>()
    var boardLines = 0
    var allMatrixesIndex = 0
    reader.forEachIndexed { index, line ->
        if (index == 0) return@forEachIndexed
        if (line.trim().isEmpty()) return@forEachIndexed

        if (boardLines % 5 == 0) {
            matrix = Array(5) { IntArray(5) }
        }

        matrix[boardLines % 5] =
            line.split("\\s".toRegex()).filter { it.isNotBlank() }.map { it.toIntOrNull() ?: 0 }.toTypedArray()
                .toIntArray()

        if (boardLines % 5 == 4) {
            allMatrixes.add(matrix)
            allMatrixesIndex++
        }
        boardLines++
    }

    for (numberDrawn in allDrawnNumbers) {
        for (aMatrix in allMatrixes) {
            for (line in aMatrix) {
                for ((index, num) in line.withIndex()) {
                    if (num == numberDrawn) {
                        line[index] = 0
                    }
                }
            }
        }

        // check if we have a winner
        var winningMatrix = Array(5) { IntArray(5) }
        var thereIsAWinner = false
        for (aMatrix in allMatrixes) {
            for (line in aMatrix) {
                if (line.all { it == 0 }) {
                    // ladies and gentlemen weeeee have a wiiiiinner
                    winningMatrix = aMatrix
                    thereIsAWinner = true
                    break
                }
            }

            for (col in aMatrix.inverseMatrix()) {
                if (col.all { it == 0 }) {
                    // ladies and gentlemen weeeee have a wiiiiinner
                    winningMatrix = aMatrix
                    thereIsAWinner = true
                    break
                }
            }
        }

        // now that we have a winning matrix (board) lets calculate the score
        if (thereIsAWinner) {
            val score = numberDrawn * winningMatrix.sumOfUnmarkedNums()

            println("Result for Part 1 == $score")
            break
        }
    }

    println("THEO")
}

private fun Array<IntArray>.sumOfUnmarkedNums(): Int {
    println(this)
    var sum = 0
    for (i in 0 until 5) {
        for (j in 0 until 5) {
            if (this[i][j] != 0) {
                println(this[i][j])
                sum += this[i][j]
            }
        }
    }

    return sum
}

private fun Array<IntArray>.inverseMatrix(): Array<IntArray> {
    val inverseMatrix: Array<IntArray> = Array(5) { IntArray(5) }

    for (y in 0 until 5) {
        inverseMatrix[y] = intArrayOf(this[0][y], this[1][y], this[2][y], this[3][y], this[4][y])
    }

    return inverseMatrix
}