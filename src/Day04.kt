fun main() {
    val input = readInput("day4")
    val ROWS = input.size
    val COLS = input[0].length
    val XMAS = "XMAS"
    fun hasXmas(r: Int, c: Int, dr: Int, dc: Int): Boolean {
        for (index in XMAS.indices) {
            val dirR = r + dr * index
            val dirC = c + dc * index
            if (dirR < 0 || dirC < 0 || dirR >= ROWS || dirC >= COLS) {
                return false
            }
            if (XMAS[index] != input[dirR][dirC]) {
                return false
            }
        }
        return true
    }
    fun part1(input: List<String>): Int {
        var count = 0
        for (row in 0 until ROWS) {
            for(col in 0 until COLS) {
                for (i in -1..1) {
                    for (j in -1..1) {
                        if (i == 0 && j == 0) {
                            continue
                        }
                        if (hasXmas(row, col, i, j)) {
                            count++
                        }
                    }
                }
            }
        }
        return count
    }

    fun hasX_mas(row: Int, col: Int): Boolean {
        if (input[row][col] != 'A') {
            return false
        }

        val dig_1 = "${input[row - 1][col - 1]}${input[row + 1][col + 1]}"
        val dig_2 = "${input[row - 1][col + 1]}${input[row + 1][col - 1]}"
        if (dig_1 in setOf("MS","SM") && dig_2 in setOf("MS","SM")) {
            return true
        }
        return false
    }
    fun part2(input: List<String>): Int {

        var count = 0
        for (row in 1 until ROWS - 1) {
            for(col in 1 until COLS - 1) {

                if (hasX_mas(row, col)) {
                    count++
                }

            }
        }
        return count
    }

    println(part1(input))
    println(part2(input))
}