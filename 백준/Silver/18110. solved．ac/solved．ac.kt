fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val m = Math.round(n * 0.15).toInt()
    val arr = IntArray (n) { (readln().toInt())}.sortedArray()
    var sum = 0.0
    for (i in m ..< n - m)
        sum += arr[i].toDouble()

    println(Math.round(sum / (n - m * 2)).toInt())
}