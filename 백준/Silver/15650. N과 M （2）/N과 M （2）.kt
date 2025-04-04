fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    close()

    val arr = IntArray(n) { it + 1 }
    val visited = BooleanArray (n) { false }

    combination(m, arr, visited, 0)
}

private fun combination(m : Int, arr : IntArray, visited : BooleanArray, depth : Int) {
    if (m == 0){
        print(arr, visited)
        return
    }

    if (depth == arr.size)
        return

    if (depth > arr.lastIndex)
        return

    visited[depth] = true
    combination(m - 1, arr, visited, depth + 1)

    visited[depth] = false
    combination(m, arr, visited, depth + 1)
}

private fun print(arr : IntArray, visited: BooleanArray) {
    for (i in 0 .. arr.lastIndex)
        if (visited[i])
            print(arr[i].toString() + " ")

    println()
}