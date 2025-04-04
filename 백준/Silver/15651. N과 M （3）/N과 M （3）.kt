fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    close()

    val arr = IntArray (n) { it + 1 }

    perm(arr, 0, m)
    println(sb.toString())
}

val sb = StringBuilder()

private fun perm(arr : IntArray, depth : Int, m : Int) {
    if (depth == m) {
        print(arr, m)
        return
    }

    for (i in arr.indices) {
        arr[depth] = i + 1
        perm(arr, depth + 1, m)
    }
}

private fun print(arr : IntArray, m : Int) =
    sb.append(arr
        .slice(0 until  m)
        .joinToString(" "))
        .append("\n")