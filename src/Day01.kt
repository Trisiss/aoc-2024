import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        val leftColumn = mutableListOf<Int>()
        val rightColumn = mutableListOf<Int>()
        input.forEach {
            val numbers = it.split("   ")
            leftColumn.add(numbers.first().toInt())
            rightColumn.add(numbers.last().toInt())
        }
        leftColumn.sort()
        rightColumn.sort()
        return leftColumn.zip(rightColumn).fold(0) { acc, pair ->
            acc + (pair.first - pair.second).absoluteValue
        }
    }

    fun part2(input: List<String>): Int {
        val leftColumn = mutableListOf<Int>()
        val rightColumn = mutableListOf<Int>()
        input.forEach {
            val numbers = it.split("   ")
            leftColumn.add(numbers.first().toInt())
            rightColumn.add(numbers.last().toInt())
        }
        return leftColumn.sumOf { leftInt -> rightColumn.count { leftInt == it } * leftInt }
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
