fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.sorted().toIntArray()
    close()

    val tmp = IntArray (m)
    comb(arr,tmp, m, 0, 0)
    println(sb.toString())
}

private fun comb(arr: IntArray, tmp : IntArray, m : Int, depth : Int, idx : Int) {
    if (depth == m) {
        toString(tmp)
        return
    }

    for (i in idx .. arr.lastIndex) {
        tmp[depth] = arr[i]
        comb(arr, tmp, m, depth + 1, i + 1)
    }
}

private val sb = StringBuilder()
private fun toString(arr : IntArray) =
    sb.append(
        arr.joinToString(" ")
    ).append("\n")