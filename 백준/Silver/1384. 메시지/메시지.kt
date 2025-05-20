fun main() = with(System.`in`.bufferedReader()) {
    var n = readln().toInt()
    var group = 0
    val sb = StringBuilder()
    while (n != 0) {
        group++
        val arr = Array (n) { readln().split(" ").toTypedArray() }
        sb.append("Group $group\n").append(solution(n, arr))
        n = readln().toInt()
        if (n != 0) sb.append("\n\n")
    }
    println(sb.toString())
}

private val negative = "was nasty about"
private val nobody = "Nobody was nasty"
private fun solution(n : Int, arr : Array<Array<String>>) : String {
    val res = StringBuilder()
    var nasty = false
    for (i in 0 until  n)
        for (j in 1 until n)
            if (arr[i][j] == "N") {
                val target = (i - j + n) % n
                res.append("${arr[target][0]} $negative ${arr[i][0]}\n")
                nasty = true
            }

    if (!nasty) res.append(nobody)
    return res.toString().trimEnd()
}