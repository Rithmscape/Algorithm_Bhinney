fun main () = with(System.`in`.bufferedReader()) {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val arr = IntArray (n + 1)

    repeat(m){
        readln().split(" ").map { it.toInt() }
            .let { (i, j, k) ->  arr.fill(k,  i, j + 1)}
    }

    println(arr.slice(1 .. n).joinToString(" "))
}