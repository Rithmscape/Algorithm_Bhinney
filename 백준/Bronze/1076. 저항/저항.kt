fun main() = with(System.`in`.buffered()) {
    val a = find(readln(), true).toString()
    val b = find(readln(), true).toString()
    val c = find(readln(), false)
    buffered().close()

    val result = (a + b).toLong() * c
    print(result)
}

private fun find(color : String, command : Boolean) : Long { // true : 저항, false : 곱셈
    return when (color) {
        "black" -> if (command) 0 else 1
        "brown" -> if (command) 1 else 10
        "red" -> if (command) 2 else 100
        "orange" -> if (command) 3 else 1_000
        "yellow" -> if (command) 4 else 10_000
        "green" -> if (command) 5 else 100_000
        "blue" -> if (command) 6 else 1_000_000
        "violet" -> if (command) 7 else 10_000_000
        "grey" -> if (command) 8 else 100_000_000
        "white" -> if (command) 9 else 1_000_000_000
        else -> 0
    }
}