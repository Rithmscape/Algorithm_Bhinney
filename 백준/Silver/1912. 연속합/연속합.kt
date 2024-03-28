import kotlin.math.max

fun main() = with(System.`in`.bufferedReader()) {
    val n = readLine().toInt()
    val arr = readLine().split(" ").map { it.toInt() }.toIntArray()
    val dp = IntArray(n) { Int.MIN_VALUE }
    dp[0] = arr[0]
    for (i in 1 .. arr.lastIndex)
        dp[i] = max(dp[i - 1] + arr[i], arr[i])
    println(dp.max())
}