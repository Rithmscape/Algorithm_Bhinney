fun main() = with(System.`in`.bufferedReader()) {
    val (n, b) = readln().split(" ")
    println(n.toLong().toString(b.toInt()).uppercase())
}