fun main() = with(System.`in`.bufferedReader()) {
    val k = readln().toInt()
    val arr = readln().split(' ')

    val list = arrayListOf<String>()
    var visited = BooleanArray(10)

    fun dfs(idx : Int, depth : Int, answer : String) {
        if (depth == k) {
            list.add(answer)
            return
        }

        for (i in 0 .. 9) {
            if (visited[i]) continue

            if (arr[depth] == ">" && idx > i) {
                visited[i] = true
                dfs(i, depth + 1, answer + i)
                visited[i] = false
            } else if (arr[depth] == "<" && idx < i) {
                visited[i] = true
                dfs(i, depth + 1, answer + i)
                visited[i] = false
            }
        }
    }

    for (i in 0 .. 9) {
        visited = BooleanArray(10)
        visited[i] = true
        dfs(i, 0, "$i")
        visited[i] = false
    }

    list.sort()
    println(list.last())
    println(list.first())
}