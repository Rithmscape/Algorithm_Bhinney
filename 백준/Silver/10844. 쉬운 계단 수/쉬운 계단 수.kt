fun main() = with(System.`in`.bufferedReader()) {
    val mod = 1_000_000_000L
    val n = readln().toInt()

    // n == 1 -> 1, 2, 3, 4, 5, 6, 7, 8, 9 ; 9개
    // n == 2 -> 10, 12, 21, 23, 32, 34, 43, 45, 54, 56, 65, 67, 76, 78, 87, 89, 98 ; 17개
    // n == 3 -> 101, 121, 123, 212, 232, 234, 323, ... ; 32개

    val dp = Array(n + 1) { LongArray(10) }
    (1 .. 9).forEach { dp[1][it] = 1L }
    (2 .. n).forEach { i ->
        (0 .. 9).forEach { j ->
            if (j > 0) dp[i][j] += dp[i-1][j-1]
            if (j < 9) dp[i][j] += dp[i-1][j+1]

            dp[i][j] %= mod
        }
    }

    print(dp[n].sum() % mod)
}