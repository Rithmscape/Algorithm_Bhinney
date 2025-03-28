import java.math.BigInteger

fun main() = with(System.`in`.bufferedReader()) {
    val n = readln()
    close()

    solution(n)
}

private fun solution(n: String) {
    val len = n.length
    val mid = len / 2
    var left = n.substring(0, mid)
    
    // 모든 9로 이루어진 케이스
    if (n.all { it == '9' }) {
        println("1" + "0".repeat(len - 1) + "1")
        return
    }
    
    // 홀수/짝수 길이 처리
    if (len % 2 == 0) {
        var candidate = left + left.reversed()
        if (BigInteger(candidate) > BigInteger(n)) {
            println(candidate)
            return
        }
        
        // 왼쪽 절반 증가
        left = (BigInteger(left) + BigInteger.ONE).toString().padStart(mid, '0')
        candidate = left + left.reversed()
        println(candidate)
    } else {
        val center = n[mid]
        var candidate = left + center + left.reversed()
        
        if (BigInteger(candidate) > BigInteger(n)) {
            println(candidate)
            return
        }
        
        // 가운데 숫자 증가
        if (center < '9') {
            val nextCenter = (center.toString().toInt() + 1).toString()
            candidate = left + nextCenter + left.reversed()
            println(candidate)
            return
        }
        
        // 가운데 숫자 9인 경우 왼쪽 절반 증가
        left = (BigInteger(left) + BigInteger.ONE).toString().padStart(mid, '0')
        candidate = left + "0" + left.reversed()
        println(candidate)
    }
}