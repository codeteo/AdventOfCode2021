const val LINE_SIZE = 12

fun main() {
    part1()
}

private fun part1() {
    val stringLines: List<String> = readInput("inputs/Day03_input")

    val columns = mutableListOf<List<Int>>()
    for (i in 0 until LINE_SIZE) {
        columns.add(stringLines.map { it.returnX(i).digitToIntOrNull() ?: throw Exception() })
    }

    val gammaRate = IntArray(LINE_SIZE)
    for ((index, column) in columns.withIndex()) {
        var numOfZeros = 0
        var numOfOnes = 0
        for (item in column) {
            if (item == 0) {
                numOfZeros++
            } else {
                numOfOnes++
            }
        }
        gammaRate[index] = if(numOfZeros > numOfOnes) 0 else 1
    }

    val epsilonRate = gammaRate.map { if (it == 0) 1 else 0 }

    var gammaRateString = ""
    for (i in gammaRate.indices) {
        gammaRateString += gammaRate[i].toString()
    }

    var epsilonRateString = ""
    for (i in epsilonRate.indices) {
        epsilonRateString += epsilonRate[i].toString()
    }

    println("Result for Part 1 = ${gammaRateString.toInt(2) * epsilonRateString.toInt(2)}")
}
/**
 * Returns X character based on given position.
 * @throws [NoSuchElementException] if the char sequence is empty.
 */
private fun CharSequence.returnX(position: Int): Char {
    if (isEmpty())
        throw NoSuchElementException("Char sequence is empty.")
    return this[position]
}


