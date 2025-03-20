import kotlin.math.sqrt

fun main() = with(System.`in`.bufferedReader()) {
    readln().split(" ")
        .map { it.toInt() }
        .let { (a, b) -> solution(a, b) }
}

private fun solution(a : Int, b : Int) {
    var ans = 0
    for (i in a .. b)
        if (isPrime(factors(i))) ans++

    println(ans)
}

private fun factors(n : Int) : Int {
    var cnt = 0
    var num = n
    while(num % 2 == 0) {
        cnt++
        num /= 2
    }

    for (i in 3 .. sqrt(num.toDouble()).toInt() step 2) {
        while (num % i == 0) {
            cnt++
            num /= i
        }
    }

    if (num > 1) cnt++

    return cnt
}

private fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    return (2..sqrt(n.toDouble()).toInt()).none { n % it == 0 }
}