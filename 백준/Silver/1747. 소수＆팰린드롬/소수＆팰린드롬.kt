fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    for (i in n ..< Int.MAX_VALUE)
        if (isPrime(i) && isPalindrome(i.toString())) {
            print(i)
            break
        }
}

fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false

    var i = 3
    while (i * i <= n) {
        if (n % i == 0) return false
        i += 2
    }
    return true
}

fun isPalindrome(n : String) = n == n.reversed()