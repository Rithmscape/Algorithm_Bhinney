fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()

    val multiply = " is a multiple of $n."
    val notMultiply = " is NOT a multiple of $n."
    
    while (true) {
        val target = readln().toInt()

        if (target == 0) break

        if (target % n == 0) println("$target$multiply")
        else  println("$target$notMultiply")
    }
}