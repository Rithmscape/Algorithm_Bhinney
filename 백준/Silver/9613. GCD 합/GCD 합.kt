fun main() = with(System.`in`.bufferedReader()) {
    var n = readln().toInt()

    while (n-- > 0) {
        val arr = readln().split(" ").map(String::toInt).drop(1)

        var answer = 0L
        for (i in 0..< arr.lastIndex)
            for (j in i + 1 .. arr.lastIndex)
                answer += gcd(arr[i], arr[j])

        println(answer)
    }
}

private fun gcd (n : Int, m : Int) : Int {
    var a = n
    var b = m

    if (a > b)
        a = b.also{ b = a }

    while (a > 0) {
        val c = b % a
        b = a
        a = c
    }

    return b
}