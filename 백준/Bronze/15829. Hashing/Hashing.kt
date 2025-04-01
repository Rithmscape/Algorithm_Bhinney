fun main() = with(System.`in`.bufferedReader()) {
    val MOD = 1234567891L
    val n = readln().toInt()

    val pow = generateSequence(1L) { it * 31 % MOD }
        .take(n)
        .toList()

    val hash = readln().take(n)
        .mapIndexed { i, c -> ((c - 'a' + 1) * pow[i]) % MOD }
        .fold(0L) { acc, value -> (acc + value) % MOD }

    println(hash)
}