@file:Suppress("IMPLICIT_CAST_TO_ANY")

import java.util.*

fun main() {

    fun part1(input: String): Int {

        val before = mutableMapOf<Int,MutableSet<Int>>()
        input.substringBefore("\n\n").lines().map { order ->
            val (f,t) = order.split("|").map { it.toInt() }
            before.putIfAbsent(t, mutableSetOf())
            before[t]?.add(f)
        }

        val order = mutableListOf<List<Int>>()
        input.substringAfter("\n\n").lines().map {
            order += it.split(",").map { it.toInt() }.toList()
        }

        fun isPossible(order: List<Int>): Boolean {
            var isOk = true

            val len = order.size
            for (i in 0 until len) {
                for (j in i + 1 until len) {
                    if (before[order[i]]!!.contains(order[j])) {
                        isOk = false
                    }
                }
            }
            return isOk
        }


        var res = 0
        order.forEach {
            if (isPossible(it)) {
                res += it[it.size / 2]
            }
        }

        return res
    }

    fun part2(input: String): Int {


        val before = mutableMapOf<Int,MutableSet<Int>>()
        input.substringBefore("\n\n").lines().map { order ->
            val (f,t) = order.split("|").map { it.toInt() }
            before.putIfAbsent(t, mutableSetOf())
            before[t]?.add(f)
        }

        val order = mutableListOf<List<Int>>()
        input.substringAfter("\n\n").lines().map {
            order += it.split(",").map { it.toInt() }.toList()
        }

        fun isPossible(order: List<Int>): Boolean {
            var isOk = true

            val len = order.size
            for (i in 0 until len) {
                for (j in i + 1 until len) {
                    if (before[order[i]]!!.contains(order[j])) {
                        isOk = false
                    }
                }
            }
            return isOk
        }


        var res = 0
        order.forEach {
            if (!isPossible(it)) {
                val sortedList = it.sortedWith(object: Comparator<Int> {
                    override fun compare(o1: Int?, o2: Int?): Int {
                        if (before[o2]!!.contains(o1)) {
                            return -1
                        }
                        if (before[o1]!!.contains(o2)) {
                            return 1
                        }
                        return 0
                    }
                })

                res += sortedList[sortedList.size / 2]
            }
        }

        return res
    }

    val input = readInputAsString("day5").trimIndent()
    println(part1(input))
    println(part2(input))
}