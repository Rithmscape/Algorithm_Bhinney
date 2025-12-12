fun main() = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map(String::toInt)
    
    print(n * m - 1)
}