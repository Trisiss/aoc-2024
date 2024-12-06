import kotlin.math.absoluteValue

fun main() {
    fun part1(input: List<String>): Int {
        var countSafe = 0
        input.forEach {
            val levels = it.split(" ").map { s -> s.toInt() }
            if (isSafe(levels)) countSafe++
        }
        return countSafe
    }

    fun part2(input: List<String>): Int {
        var countSafe = 0
        input.forEach {
            val levels = it.split(" ").map { s -> s.toInt() }
            if (isSafe(levels, allowUnsafeLevel = true)) countSafe++
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
    var isUp = true
    var isDown = true
    for (leftIndex in 0 until levels.lastIndex) {
        val leftValue = levels[leftIndex]
        val rightValue = levels[leftIndex + 1]
        if ((leftValue - rightValue).absoluteValue !in 1..3) {
            return false
        }
        isUp = isUp && rightValue > leftValue
        isDown = isDown && leftValue > rightValue
        if (!isUp && !isDown) {
            return false
        }
    }
    return true
}