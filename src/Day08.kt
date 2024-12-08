import kotlin.math.abs

data class Antenna(val char: Char, val location: Pair<Int,Int>)

fun main() {
    fun part1(input: List<String>): Int {
        val rows = input.size
        val cols = input[0].length
        val antennas = mutableSetOf<Antenna>()
        val antinodes = mutableSetOf<Pair<Int,Int>>()

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (input[i][j] != '.') {
                    antennas += Antenna(input[i][j], i to j)
                }
            }
        }
        val charToAntenna = antennas.groupBy { it.char }

        for (key in charToAntenna.keys) {

            val values = charToAntenna[key] ?: emptyList()
            val sz = values.size

            for (i in 0 until sz) {
                val from = values[i]
                for (j in i + 1 until sz) {
                    val to = values[j]

                    val (fromR, fromC) = from.location
                    val (toR, toC) = to.location
                    val dr = toR - fromR
                    val dc = toC - fromC

                    if (dr != 0 || dc != 0) {
                        antinodes += fromR - dr to fromC - dc
                        antinodes += toR + dr to toC + dc
                    }
                }
            }
        }

        return antinodes.count { it.first in 0..<rows && it.second in 0..<cols }
    }

    fun part2(input: List<String>): Int {
        val rows = input.size
        val cols = input[0].length
        val antennas = mutableSetOf<Antenna>()
        val antinodes = mutableSetOf<Pair<Int,Int>>()

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (input[i][j] != '.') {
                    antennas += Antenna(input[i][j], i to j)
                }
            }
        }
        val charToAntenna = antennas.groupBy { it.char }

        for (key in charToAntenna.keys) {

            val values = charToAntenna[key] ?: emptyList()
            val sz = values.size

            for (i in 0 until sz) {
                val from = values[i]
                for (j in i + 1 until sz) {
                    val to = values[j]

                    val (fromR, fromC) = from.location
                    val (toR, toC) = to.location
                    val dr = toR - fromR
                    val dc = toC - fromC

                    if (dr != 0 || dc != 0) {
                        var fromDr = fromR
                        var fromDc = fromC
                        while (fromDr  in 0..<rows && fromDc in 0..<cols) {
                            antinodes += fromDr to fromDc
                            fromDr -= dr
                            fromDc -= dc
                        }
                        var toDr = toR
                        var toDc = toC
                        while (toDr  in 0..<rows && toDc in 0..<cols) {
                            antinodes += toDr to toDc
                            toDr += dr
                            toDc += dc
                        }
                    }
                }
            }
        }

        return antinodes.count { it.first in 0..<rows && it.second in 0..<cols }
    }

    val input = readInput("day8")
    println(part1(input))
    println(part2(input))
}