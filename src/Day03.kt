import java.util.Deque

fun main() {
    val mulRegex = """mul\((\d{1,3}),(\d{1,3})\)""".toRegex()
    val doRegex = """do\(\)""".toRegex()
    val dontRegex = """don't\(\)""".toRegex()

    fun getMultiplication(section: String): Long {
        println(section)
        return mulRegex.findAll(section).sumOf {
            val (first, second) = it.destructured
            first.toLong() * second.toLong()
        }
    }

    fun part1() {
        val input = readInput("day3")
        val res = input.sumOf { line ->
            getMultiplication(line)
        }
        println(res)
    }

    fun part2() {
        val input = readInputAsString("day3")
        var sum = 0L
        var enabled = true

        """$mulRegex|$dontRegex|$doRegex""".toRegex().findAll(input).forEach { match: MatchResult ->
            when(match.value) {
                "don't()" -> enabled = false
                "do()" -> enabled = true
                else -> if (enabled) sum += match.mulNumbers()
            }
        }
        println(sum)
    }
    part1()
    part2()

}


private fun MatchResult.mulNumbers(): Long {
    val (first, second) = this.destructured
    return first.toLong() * second.toLong()
}