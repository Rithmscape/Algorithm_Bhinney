fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt() // 포도주 개수
    val wine = IntArray(n + 1) { if (it == 0) 0 else readln().toInt()} // 포도주
    if (n == 1)
       return@with print(wine[1])
    if (n == 2)
        return@with print(wine[1] + wine[2])

    val dp = IntArray (n + 1)
    dp[1] = wine[1]
    dp[2] = wine[1] + wine[2]

    for (i in 3 .. n)
        dp[i] = maxOf(
            dp[i - 1], // 현재 포도주를 마시지 X
            wine[i] + wine[i - 1] + dp[i - 3], // 현재와 이전 포도주를 마심
            wine[i] + dp[i - 2] // 현재 포도주만 마심
        )

    print(dp[n])
}