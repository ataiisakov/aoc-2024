import java.math.BigInteger

fun main() {
    fun part1(input: List<String>): BigInteger {
        fun solve(numbers: List<BigInteger>, index: Int, cur: BigInteger, target: BigInteger): Boolean {
            if (cur == target) return true
            if (cur > target) return false
            if (index == numbers.size) return false
            return solve(numbers, index + 1, cur + numbers[index], target) ||
                    solve(numbers, index + 1, cur * numbers[index], target)

        }
        var res: BigInteger = BigInteger.ZERO
        input.forEach {
            val (first, second) = it.split(":")
            val target = first.toBigInteger()
            val numbers = second.substringAfter(" ").split(" ").map { it.toBigInteger() }.toList()
            if (solve(numbers, 1, numbers[0], target)) {
                res += target
            }
        }
        return res
    }

    fun part2(input: List<String>): BigInteger {
        fun solve2(numbers: List<BigInteger>, index: Int, cur: BigInteger, target: BigInteger): Boolean {
            if (cur > target) return false
            if (index == numbers.size) return cur == target

            return solve2(numbers, index + 1, cur + numbers[index], target) ||
                    solve2(numbers, index + 1, cur * numbers[index], target) ||
                    solve2(numbers, index + 1, (cur.toString() + numbers[index].toString()).toBigInteger(), target)

        }
        var res: BigInteger = BigInteger.ZERO
        input.forEach {
            val (first, second) = it.split(":")
            val target = first.toBigInteger()
            val numbers = second.substringAfter(" ").split(" ").map { it.toBigInteger() }.toList()
            if (solve2(numbers, 1, numbers[0], target)) {
                res += target
            }
        }
        return res
    }

    val input = readInput("day7")
    println(part1(input))
    println(part2(input))
}