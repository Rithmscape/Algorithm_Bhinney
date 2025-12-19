fun main() = with(System.`in`.bufferedReader()) {
    val (a, b) = readln().split(" ").map(String::toBigInteger)
    print(a.plus(b))
}