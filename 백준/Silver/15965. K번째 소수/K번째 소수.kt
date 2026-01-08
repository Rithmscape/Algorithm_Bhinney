import java.util.Scanner
import kotlin.math.sqrt

fun main() {
    val sc = Scanner(System.`in`)
    val k = sc.nextInt()
    sc.close()

    val prime = isPrime()
    val list = mutableListOf<Long>()

    for (i in 2L .. prime.lastIndex) {
        if (!prime[i.toInt()]) list.add(i)
        if (list.size == k) break
    }

    println(list[k - 1])
}

private fun isPrime() : BooleanArray {
    val prime = BooleanArray(800_002) { false }
    prime[0] = true
    prime[1] = true

    for (i in 2 .. sqrt(prime.size.toDouble()).toInt()) {
        if (prime[i]) continue

        for (j in i * i ..< prime.size step i)
            prime[j] = true

    }

    return prime
}
