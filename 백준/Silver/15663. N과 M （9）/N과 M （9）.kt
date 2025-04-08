fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.sorted().toIntArray()
    val visited = BooleanArray(n)
    val result = IntArray(m)
    close()

    perm(arr, visited, result, m, 0)
    list = list.distinct().toMutableList()
    println(list.joinToString("\n"))
}
private var list = mutableListOf<String>()
private fun perm(arr: IntArray, visited: BooleanArray, result: IntArray, m: Int, depth: Int) {
    if (depth == m) {
        list.add(result.joinToString(" "))
        return
    }

    for (i in arr.indices) {
        if (visited[i]) continue

        visited[i] = true
        result[depth] = arr[i]
        perm(arr, visited, result, m, depth + 1)
        visited[i] = false
    }
}