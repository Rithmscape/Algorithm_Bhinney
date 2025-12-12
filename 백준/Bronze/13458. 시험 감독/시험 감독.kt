fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt() // 시험장 개수
    val arr = readln()
        .split(" ").map(String::toInt) // 각 시험장의 응시자 수
    val (b, c) = readln()
        .split(" ").map(String::toInt) // 총 감독관 감시 가능 응시자, 부 감독관 감시 가능 응시자

    var answer = n.toLong()
    arr.filter { it - b > 0 }
        .forEach {
            answer += if ((it - b) % c == 0) (it - b) / c else (it - b) / c + 1
        }

    print(answer)
}