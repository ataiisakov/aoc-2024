import kotlin.math.abs

fun main() {

    fun part1() {
        val (left, right) = readInput("day1").map { line ->
            val left = line.substringBefore(" ").toInt()
            val right = line.substringAfterLast(" ").toInt()
            left to right
        }.unzip()
        val res = left.sorted().zip(right.sorted()).sumOf { (first, second) -> abs(first - second) }
        println(res)
    }
    fun part2() {
        val (left, right) = readInput("day1").map { line ->
            val left = line.substringBefore(" ").toInt()
            val right = line.substringAfterLast(" ").toInt()
            left to right
        }.unzip()
        val freq: Map<Int, Int> = right.groupingBy { it }.eachCount()

        val res = left.sumOf { it * freq.getOrDefault(it, 0)   }
        println(res)
    }
    part1()
    part2()

}
