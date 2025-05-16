fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val graph = Array (100) { BooleanArray(100) }
    var cnt = 0

    repeat(n) {
        val (x, y) = readln().split(" ").map(String::toInt)

        for (i in x until x + 10)
            for (j in y until y + 10) {
                if (graph[i][j]) continue
                graph[i][j] = true
                cnt++
            }
    }

    println(cnt)
}