fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    close()

    val arr = IntArray(n) { it + 1 }
    comb(arr, 0, 0, m)
    println(sb.toString())
}

private fun comb(arr : IntArray, depth : Int, idx : Int,  m : Int) {
    if (depth == m) {
        toString(arr, m)
        return
    }

    for (i in idx .. arr.lastIndex) {
        arr[depth] = i + 1
        comb(arr, depth + 1, i, m)
    }
}

private val sb = StringBuilder()

private fun toString(arr : IntArray, m : Int) =
    sb.append(
        arr.slice(0 until m)
            .joinToString(" "))
        .append("\n")