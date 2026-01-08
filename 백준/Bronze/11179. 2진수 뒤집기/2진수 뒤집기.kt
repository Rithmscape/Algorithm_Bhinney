import java.util.Scanner

fun main() {
    val sc = Scanner(System.`in`);
    val n = sc.nextInt() // 입력받은 수 n
        .toString(2) // 2진법 변환
        .reversed() // 뒤집어
        .toInt(2) // 10진법

    sc.close()

    println(n)
}
