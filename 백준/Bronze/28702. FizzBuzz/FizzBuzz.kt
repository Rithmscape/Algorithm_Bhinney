fun main() = with(System.`in`.bufferedReader()) {
    var f = false
    var num = 0

    repeat(3) {
        val str = readln()

        if (str[0] != 'F' && str[0] != 'B') {
            num = str.toInt()
            f = true
        }

        if (f) num++
    }
    close()

    if (num % 3 == 0 && num % 5 == 0) println("FizzBuzz")
    else if (num % 3 == 0) println("Fizz")
    else if (num % 5 == 0) println("Buzz")
    else println(num)
}