fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }.sorted().toIntArray()
    val visited = BooleanArray(n)
    val result = IntArray(m) // 결과를 저장할 배열 추가
    close()

    perm(arr, visited, result, m, 0)
}

private fun perm(arr: IntArray, visited: BooleanArray, result: IntArray, m: Int, depth: Int) {
    if (depth == m) {
        print(result)
        return
    }

    for (i in arr.indices) {
        if (visited[i]) continue

        visited[i] = true
        result[depth] = arr[i] // 원본 배열 대신 결과 배열에 값 저장
        perm(arr, visited, result, m, depth + 1)
        visited[i] = false
    }
}

private fun print(arr: IntArray) =
    arr.joinToString(" ")
        .let(::println)