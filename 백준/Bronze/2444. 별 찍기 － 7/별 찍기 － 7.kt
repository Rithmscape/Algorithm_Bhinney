fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val ans = StringBuilder()
    var cnt = 1

    for (i in 1 ..  n) {
        repeat(n - i) {ans.append(" ")}
        repeat(cnt) {ans.append("*")}
        ans.append("\n")
        cnt += 2
    }

    cnt -= 4
    for (i in n - 1 downTo  1) {
        repeat(n - i) {ans.append(" ")}
        repeat(cnt) {ans.append("*")}
        ans.append("\n")
        cnt -= 2
    }

    println(ans.toString().trimEnd())
}