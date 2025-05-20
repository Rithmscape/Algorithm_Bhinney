fun main() = with(System.`in`.bufferedReader()) {
    val ans = StringBuilder()
    val t = readln().toInt()
    val cost = intArrayOf(25, 10, 5, 1)
    repeat(t) {
        val arr = IntArray(4)
        var n = readln().toInt()

        for (i in 0 .. 3) {
            arr[i] = n / cost[i]
            n %= cost[i]
        }

        ans.append(arr.joinToString(" ")).append("\n")
    }

    println(ans.toString())
}