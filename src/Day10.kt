fun main() {
    fun part1(input: List<String>): Int {
        val rows = input.size
        val cols = input[0].length

        fun dfs(i: Int, j: Int, want: Char, vis: MutableSet<Pair<Int, Int>>): Int {
            if (i < 0 || j < 0 || i >= rows || j >= cols) {
                return 0
            }

            if (input[i][j] != want) {
                return 0
            }
            if (vis.contains(i to j)) {
                return 0
            }
            vis += i to j
            if (input[i][j] == '9') {
                return  1
            }
            return dfs(i + 1, j, input[i][j] + 1, vis) + dfs(i - 1, j, input[i][j] + 1, vis) + dfs(i, j + 1, input[i][j] + 1, vis) + dfs(i, j - 1, input[i][j] + 1, vis)

        }
        var count = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                count += dfs(i, j, '0', mutableSetOf<Pair<Int,Int>>())
            }
        }

        return count
    }

    fun part2(input: List<String>): Int {
        val rows = input.size
        val cols = input[0].length

        fun dfs(i: Int, j: Int, want: Char): Int {
            if (i < 0 || j < 0 || i >= rows || j >= cols) {
                return 0
            }

            if (input[i][j] != want) {
                return 0
            }
            if (input[i][j] == '9') {
                return  1
            }
            return dfs(i + 1, j, input[i][j] + 1) + dfs(i - 1, j, input[i][j] + 1) + dfs(i, j + 1, input[i][j] + 1) + dfs(i, j - 1, input[i][j] + 1)

        }
        var count = 0

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                count += dfs(i, j, '0')
            }
        }

        return count
    }

    val input = readInput("day10")
    println(part1(input))
    println(part2(input))
}