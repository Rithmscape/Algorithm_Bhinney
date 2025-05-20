import java.util.Scanner

fun main() {
    val scanner = Scanner(System.`in`)
    val n = scanner.nextInt()
    val dots = Math.pow(2.0, n.toDouble()).toInt() + 1
    val total = dots * dots
    
    println(total)
}