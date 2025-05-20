fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = IntArray (n + 1) { it }
    repeat(m) {
        readln().split(" ").map { it.toInt() }
            .let { (i, j) ->
                val tmp = arr[i]
                arr[i] = arr[j]
                arr[j] = tmp
            }
    }
    println(arr.slice(1 .. n).joinToString(" "))
}