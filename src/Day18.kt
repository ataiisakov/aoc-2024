import java.util.Deque
import java.util.Queue

fun main() {

    fun bfs(vis: MutableSet<Pair<Int,Int>>): Int {
        val q = ArrayDeque<Triple<Int,Int,Int>>()
        val directions = listOf(
            -1 to 0, // W
            0 to -1, // N
            1 to 0, // E
            0 to 1 // S
        )
        q += Triple(0,0,0)
        vis += 0 to 0

        val gridSize = 70

        while (!q.isEmpty()) {

            var sz = q.size
            while (sz-- > 0) {
                val cur = q.removeFirst()
                if (cur.first == gridSize && cur.second == gridSize) {
                    return cur.third
                }
                for (nei in directions) {
                    val dx = cur.first + nei.first
                    val dy = cur.second + nei.second
                    if (dx in 0..gridSize && dy in 0..gridSize && !vis.contains(dx to dy)) {
                        vis += dx to dy
                        q += Triple(dx,dy,cur.third + 1)
                    }
                }
            }
        }
        return -1
    }
    fun part1(input: List<String>): Int {
        val bytes = input.take(1024).map {
            val (x,y) = it.split(",")
            x.toInt() to y.toInt()
        }.toSet()
        return bfs(bytes.toMutableSet())
    }

    fun part2(input: List<String>): Pair<Int,Int> {
        val bytes = input.map {
            val (x,y) = it.split(",")
            x.toInt() to y.toInt()
        }
        val vis = mutableSetOf<Pair<Int,Int>>()
        bytes.forEach {
            vis += it
            val res = bfs(vis.toMutableSet())
            if (res == -1) {
                return it
            }
        }
        return 0 to 0
    }

    val input = readInput("day18")
    println(part1(input))
    println(part2(input))
}