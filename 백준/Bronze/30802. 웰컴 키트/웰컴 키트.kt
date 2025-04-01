fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val size = readln().split(" ").map { it.toInt() }
    val (t, p) = readln().split(" ").map { it.toInt() }

    var shirts = 0
    for (i in 0 .. 5) {
        shirts += size[i] / t
        if (size[i] % t != 0) shirts++
    }

    val pens = n / p
    val pencil = n % p

    println("$shirts\n$pens $pencil")
}