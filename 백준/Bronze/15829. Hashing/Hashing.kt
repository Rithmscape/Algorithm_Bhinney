import kotlin.math.pow

fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val hash = readln()
        .mapIndexed{ i, c -> (c - '`') * 31.0.pow(i).toInt() }
        .sum()

    println(hash)
}