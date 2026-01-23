fun main(){
    val chase = init()
    val input = input()

    var answer = 0

    for (i in 0 until 8)
        for (j in 0 until 8)
            if (chase[i][j] && input[i][j] == 'F') answer++

    println(answer)
}

private fun input() : Array<CharArray> {
    val input = Array (8) { CharArray(8) }

    for (i in 0 until 8)
        input[i] = readln().toCharArray()

    return input
}


private fun init() : Array<BooleanArray> {
    // 백 : true, 흑 : false
    val chase = Array (8) { BooleanArray(8) }

    for (i in 0 until 8)
        for (j in 0 until 8)
            chase[i][j] = (i + j) % 2 == 0

    return chase
}