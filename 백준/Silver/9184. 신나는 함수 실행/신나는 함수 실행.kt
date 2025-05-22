fun main() = with(System.`in`.bufferedReader()) {
    val sb = StringBuilder() // 결과 값을 StringBuilder에 담기
    while (true) {
        val (a, b, c) = readln().split(" ").map(String::toInt)

        // 세 변수가 모두 -1이면 끝내기
        if (a == -1 && b == -1 && c == -1) break
        val res = w(a, b, c)

        sb.append("w($a, $b, $c) = $res").append("\n") // 줄바꿈 보기 좋게 따로 append 하기
    }

    print(sb.toString())
}
private val dp = Array (21) { Array(21) { IntArray(21) { -1 } } }

private fun w(a: Int, b: Int, c: Int): Int {
    
    // 이미 값이 있는데 재귀를 돌면 아까우니까 미리 제거
    // 범위가 넘어가는 경우가 있으므로 범위 확인해서 제거
    if (checkRange(a, b, c) && dp[a][b][c] != -1) return dp[a][b][c]

    if (a <= 0 || b <= 0 || c <= 0) return 1

    if (a > 20 || b > 20 || c > 20) return w(20, 20, 20)

    if (a < b && b < c)
        dp[a][b][c] = w(a, b, c-1) + w(a, b-1, c-1) - w(a, b-1, c)
    else
        dp[a][b][c] = w(a-1, b, c) + w(a-1, b-1, c) + w(a-1, b, c-1) - w(a-1, b-1, c-1)

    return dp[a][b][c]
}

private fun checkRange(a : Int, b : Int, c : Int) : Boolean
    = a > 0 && b > 0 && c > 0 && a <= 20 && b <= 20 && c <= 20