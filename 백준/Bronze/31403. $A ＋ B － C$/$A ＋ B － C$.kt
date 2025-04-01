fun main() = with(System.`in`.bufferedReader()) {
    val a = readln()
    val b = readln()
    val c = readln()
    close()
    solution(a, b, c)
}

private fun solution(a : String, b : String, c: String) {
    println(a.toInt() + b.toInt() - c.toInt())
    println((a + b).toInt() - c.toInt())
}