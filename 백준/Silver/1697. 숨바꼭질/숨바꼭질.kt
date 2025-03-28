import java.util.LinkedList

fun main() = with(System.`in`.bufferedReader()) {
    val (n, k)= readln().split(" ").map { it.toInt() }
    val visited = IntArray(100001) { 0 }
    bfs(n, k, visited)
    println(visited[k])
}

private fun bfs(n : Int, k : Int, visited: IntArray) {
    val queue = LinkedList<Int>()

    queue.add(n)
    visited[n] = 1

    var now = 0
    while (queue.isNotEmpty()) {
        now = queue.remove()

        if (now == k) {
            visited[now] -= 1
            return
        }

        if (now - 1 >= 0 && visited[now - 1] == 0) {
            visited[now - 1] = visited[now] + 1
            queue.offer(now - 1)
        }

        if (now + 1 < visited.size && visited[now + 1] == 0) {
            visited[now + 1] = visited[now] + 1
            queue.offer(now + 1)
        }

        if (2 * now < visited.size && visited[2 * now] == 0) {
            visited[2 * now] = visited[now] + 1
            queue.offer(2 * now)
        }
    }
}