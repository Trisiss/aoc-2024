import kotlin.math.abs
import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        var countSafe = 0
        input.forEach {
            val levels = it.split(" ").map { s -> s.toInt() }
            if (isSafe(levels))
            countSafe++
        }
        return countSafe
    }

    fun part2(input: List<String>): Int {
        var countSafe = 0
        input.forEach {
            val levels = it.split(" ").map { s -> s.toInt() }
           if (isSafe(levels, true))
            countSafe++
        }
        return countSafe
    }



    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

    fun isSafe(levels: List<Int>, allowUnsafeLevel: Boolean = false): Boolean {
        var isSafe = false
        if (allowUnsafeLevel) {
            for (i in levels.indices) {
                val skip = levels.toMutableList().apply { removeAt(i) }
                isSafe = isSafe || checkLevels(skip)
            }
        } else {
            isSafe = checkLevels(levels)
        }
        return isSafe
    }

    fun checkLevels(levels: List<Int>): Boolean {
        var isBiggest = true
        var isSmallest = true
        for (leftIndex in 0 until levels.lastIndex) {
            val rightIndex = leftIndex + 1
            val leftValue = levels[leftIndex]
            val rightValue = levels[rightIndex]
            if (abs(leftValue - rightValue) !in 1..3) {
                return false
            }
            isBiggest = isBiggest && rightValue > leftValue
            isSmallest = isSmallest && leftValue > rightValue
            if (!isBiggest && !isSmallest) {
                return false
            }
        }
        return true
    }

val NO_VALUE = -1 to -1
