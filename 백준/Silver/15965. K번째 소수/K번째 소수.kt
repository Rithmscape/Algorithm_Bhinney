import java.util.Scanner
import kotlin.math.sqrt

fun main() {
    val sc = Scanner(System.`in`)
    val k = sc.nextInt()
    sc.close()

    val limit = 8_000_000
    val prime = isPrime(limit)

    var count = 0
    for (i in 2 until limit) {
        if (!prime[i]) { count++
            if (count == k) {
                println(i)
                return
            }
        }
    }
}

private fun isPrime(limit : Int) : BooleanArray {
    val prime = BooleanArray(limit) { false }
    prime[0] = true
    prime[1] = true


    for (i in 2..sqrt(limit.toDouble()).toInt()) {
        if (prime[i]) continue
        for (j in i * i until limit step i) {
            prime[j] = true
        }
    }

    return prime
}