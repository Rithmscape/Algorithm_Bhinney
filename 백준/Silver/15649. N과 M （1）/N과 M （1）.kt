fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    close()

    val arr = IntArray (n) {it + 1}
    val visited = BooleanArray(n)
    perm(arr, visited, m, 0)
}

private fun perm(arr : IntArray, visited : BooleanArray, m : Int, depth : Int) {
    if (depth == m) {
        print(arr, m)
        return
    }

    for (i in arr.indices) {
        if (visited[i]) continue

        visited[i] = true
        arr[depth] = i + 1
        perm(arr, visited, m, depth + 1)
        visited[i] = false
    }
}


private fun print(arr: IntArray, m : Int) =
    arr.slice(0 until  m)
        .joinToString(" ")
        .let ( :: println )