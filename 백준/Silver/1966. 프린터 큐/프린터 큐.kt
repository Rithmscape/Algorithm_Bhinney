import java.util.*


fun main() = with(System.`in`.bufferedReader()) {
    val t = readln().toInt() // 테스트 개수

    repeat(t) {
        val (n, m) = readln().split(" ").map { it.toInt() }
        val priorities = readln().split(" ").map { it.toInt() }
        val queue = LinkedList<Pair<Int, Int>>()

        for (i in 0 until  n)
            queue.offer(Pair(i, priorities[i]))

        var cnt = 0

        while (queue.isNotEmpty()) {
            val cur = queue.poll()

            val isHighestPriority = queue.any { it.second > cur.second }
            if (isHighestPriority)
                queue.offer(cur)
            else {
                cnt++

                if (cur.first == m){
                    println(cnt)
                    break
                }
            }
        }
    }
}