fun main() = with(System.`in`.bufferedReader()) {
    val mod = 15746
    val n = readln().toInt()

    // n == 1 -> 1
    // n == 2 -> 00, 11
    // n == 3 -> 001, 100, 111
    // n == 4 -> 0000, 0011, 1001, 1100, 1111
    // n == 5 -> 00001, 00100, 00111, 10000, 10011, 11001, 11100, 11111

    if (n == 1) {
        print(1)
        return@with
    }

    if (n == 2) {
        print(2)
        return@with
    }

    val dp = IntArray (n + 1)
    dp[1] = 1
    dp[2] = 2

    for (i in 3 .. n)
        dp[i] = (dp[i - 2] + dp[i - 1]) % mod

    print(dp[n])
}