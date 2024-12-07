fun main() {
    fun part1(input: String): Int {
        val reg = "mul[(]\\d+,\\d+[)]".toRegex()
        val result = reg.findAll(input).toList()
        return result.sumOf {
            mul(it)
        }
    }

    fun part2(input: String): Int {
        val dontReg = "don\'t[(][)]".toRegex()
        val doReg = "do[(][)]".toRegex()
        val reg = "mul[(]\\d+,\\d+[)]".toRegex()
        val resultDont = dontReg.findAll(input)
        val resultDo = doReg.findAll(input)
        val resultMul = reg.findAll(input)
        val result = resultDont + resultDo + resultMul
        var enableMul = true
        return result.sortedBy { it.range.first }.sumOf {
            when {
                dontReg.containsMatchIn(it.value) -> {
                    enableMul = false
                    0
                }

                doReg.containsMatchIn(it.value) -> {
                    enableMul = true
                    0
                }

                reg.containsMatchIn(it.value) -> {
                    if (!enableMul) {
                        return@sumOf 0
                    }
                    mul(it)
                }

                else -> error("Not found regex for $it 10916163")
            }

        }
    }


    // Read the input from the `src/Day03.txt` file.
    val input = readInputText("Day03")
    part1(input).println()
    part2(input).println()
}

fun mul(match: MatchResult): Int = with(match) {
    val firstValue = value.substring(
        value.indexOf('(') + 1,
        value.indexOf(',')
    ).toInt()
    val secondValue = value.substring(
        value.indexOf(',') + 1,
        value.indexOf(')')
    ).toInt()
    firstValue * secondValue
}