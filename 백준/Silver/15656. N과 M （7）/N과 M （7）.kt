fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.sorted().toIntArray()
    close()

    val tmp = IntArray(m)
    perm(arr, tmp, 0, m)
    println(sb.toString())
}

private fun perm(arr : IntArray, tmp : IntArray, depth : Int, m : Int) {
    if (depth == m) {
        toPrint(tmp)
        return
    }

    for (i in arr.indices) {
        tmp[depth] = arr[i]
        perm(arr, tmp, depth + 1, m)
    }
}

private val sb = StringBuilder()

private fun toPrint(arr : IntArray) =
    sb.append(
        arr.joinToString(" ")
    ).append("\n")