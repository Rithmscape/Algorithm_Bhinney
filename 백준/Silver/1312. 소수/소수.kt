fun main() = with(System.`in`.bufferedReader()) {
    var (a, b, n) = readLine().split(" ").map { it.toInt() }
    var ans = 0

    repeat(n) {
        a = (a % b) * 10
        ans = a / b
    }

    println(ans)
}