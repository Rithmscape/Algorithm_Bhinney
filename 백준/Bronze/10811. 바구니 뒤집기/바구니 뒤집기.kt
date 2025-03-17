fun main(args: Array<String>) = with(System.`in`.bufferedReader()) {
    val (n, m) = readLine().split(" ").map { it.toInt() }
    val list = mutableListOf<IntArray>()

    for (i in 0 ..< m) {
        val arr = readLine().split(" ").map { it.toInt() }.toIntArray()
        list.add(arr)
    }

    solution(n, m, list);
}

fun solution(n : Int, m: Int, list: MutableList<IntArray>) {
    // 바구니를 총 n개 (1 .. n)
    // m 번의 바구니 순서를 역순으로
    // 바구니의 번호를 왼쪽부터 출력

    var baskets = (1 .. n).toMutableList()

    for (i in 0 ..< m) {
        val start = list[i][0] - 1
        val end = list[i][1] - 1

        val section = baskets.slice(start .. end).reversed()

        for (j in start .. end) {
            baskets[j] = section[j - start]
        }
    }

    println(baskets.joinToString(" "))
}