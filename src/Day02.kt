import kotlin.math.abs
import kotlin.math.absoluteValue

fun main() {
    fun isLevelSafe(level: List<Int>): Boolean {
        var isIncreasing = true
        var inDecreasing = true
        var isSafe = true

        for (i in 0..< level.lastIndex) {
            val a = level[i]
            val b = level[i+1]

            when {
                a < b -> isIncreasing = false
                a > b -> inDecreasing = false
                else -> {
                    isIncreasing = false
                    inDecreasing = false
                }
            }
            isSafe = isSafe && (a - b).absoluteValue <= 3
        }
        return isSafe && (isIncreasing || inDecreasing)
    }
    fun part1() {
        val levels: List<List<Int>> = readInput("day2").map { line ->
            line.split(" ").map { it.toInt() }.toList()
        }.toList()

        val res = levels.count { isLevelSafe(it) }
        println(res)
    }
    fun part2() {
        val levels: List<List<Int>> = readInput("day2").map { line ->
            line.split(" ").map { it.toInt() }.toList()
        }.toList()

        var count = 0
        levels.forEach { level ->
            var isSafe = false
            for (index in 0..level.lastIndex) {
                isSafe = isLevelSafe(level.toMutableList().apply { removeAt(index) })
                if (isSafe) break
            }
            if (isSafe) count++
        }
        println(count)
    }
    part1()
    part2()

}
