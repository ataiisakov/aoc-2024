enum class GuardDirection(val pos: Pos) {
    NORTH(Pos(-1, 0)),
    EAST(Pos(0, 1)),
    SOUTH(Pos(1, 0)),
    WEST(Pos(0, -1));

    fun turnRight(): GuardDirection {
        return when(this) {
            NORTH -> EAST
            EAST -> SOUTH
            SOUTH -> WEST
            WEST -> NORTH
        }
    }
}

data class Pos(val row: Int, val col: Int) {
    operator fun plus(other: Pos) = Pos(row + other.row, col + other.col)
}
fun main() {
    fun part1(input: List<String>): Int {
        val rows = input.size
        val cols = input[0].length
        var iniPos = Pos(0, 0)
        val obstacles = mutableSetOf<Pos>()
        var dir = GuardDirection.NORTH
        for(r in 0 until rows) {
            for (c in 0 until cols) {
                if (input[r][c] == '^') {
                    iniPos = Pos(r, c)
                }
                if (input[r][c] == '#') {
                    obstacles += Pos(r, c)
                }
            }
        }
        var position = iniPos

        fun turnRight() {
            dir = dir.turnRight()
        }
        fun nextPos(pos: Pos): Pos {
            return pos + dir.pos
        }

        val visited = mutableSetOf<Pos>()

        while (position.row in 0..<rows && position.col in 0..< cols) {
            visited += position
            val next = nextPos(position)
            if (obstacles.contains(next)) {
                turnRight()
            } else {
                position = next
            }
        }

        return visited.size
    }

    fun part2(input: List<String>): Int {
        val board = input.map { it.toMutableList() }
        val rows = input.size
        val cols = input[0].length
        var res = 0


        var iniPos = Pos(0, 0)
        val obstacles = mutableSetOf<Pos>()
        for(r in 0 until rows) {
            for (c in 0 until cols) {
                if (input[r][c] == '^') {
                    iniPos = Pos(r, c)
                }
                if (input[r][c] == '#') {
                    obstacles += Pos(r, c)
                }
            }
        }

        for (i in 0 until rows) {
            for (j in 0 until cols) {
                if (board[i][j] == '.') {
                    obstacles += Pos(i,j)

                    var position = iniPos
                    var dir = GuardDirection.NORTH

                    fun turnRight() {
                        dir = dir.turnRight()
                    }
                    fun nextPos(): Pos {
                        return position + dir.pos
                    }

                    val visitedState = mutableSetOf<Pair<Pos,Pos>>()

                    while (position.row in 0..<rows && position.col in 0..< cols) {
                        if (position to dir.pos in visitedState) {
                            res++
                            break
                        }
                        visitedState += position to dir.pos

                        val next = nextPos()
                        if (obstacles.contains(next)) {
                            turnRight()
                        } else {
                            position = next
                        }
                    }

                    obstacles -= Pos(i,j)
                }
            }
        }

        return res
    }

    val input = readInput("day6")
    println(part1(input))
    println(part2(input))
}