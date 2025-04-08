fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.sorted().toIntArray()
    close()

    val tmp = IntArray(m)
    comb(arr, tmp, 0, m, 0)
    println(sb.toString())
}

private fun comb(arr : IntArray, tmp : IntArray, depth : Int, m : Int, idx : Int) {
    if (depth == m) {
        toString(tmp)
        return
    }

    for (i in idx .. arr.lastIndex) {
        tmp[depth] = arr[i]
        comb(arr, tmp, depth + 1, m, i)
    }
}

private val sb = StringBuilder()
private fun toString(arr : IntArray) =
    sb.append(
        arr.joinToString(" ")
    ).append("\n")