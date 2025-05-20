fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val ans = StringBuilder()

    for (i in 1 ..  n)
        ans.append(" ".repeat(n - i))
            .append("*".repeat(2 * i - 1))
            .append("\n")

    for (i in n - 1 downTo  1)
        ans.append(" ".repeat(n - i))
            .append("*".repeat(2 * i - 1))
            .append("\n")

    println(ans.toString().trimEnd())
}