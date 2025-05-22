fun main() = with(System.`in`.bufferedReader()) {
    val t = readln().toInt()

    // 1, 1, 1, 2, 2, 3, 4, 5, 7, 9, 12, 16, 21, 28, 37 ..
    val dp = LongArray(101)
    dp[1] = 1
    dp[2] = 1
    dp[3] = 1
    dp[4] = 2
    dp[5] = 2
    for (i in 6 .. 100)
        dp[i] = dp[i - 1] + dp[i - 5]
    repeat(t) {
        println(dp[readln().toInt()])
    }
}