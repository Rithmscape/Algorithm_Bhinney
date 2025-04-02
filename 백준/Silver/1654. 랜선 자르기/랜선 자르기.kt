fun main() = with(System.`in`.bufferedReader()) {

    // 영식이 가지고 있는 랜선 k개, 캠프 때 쓸 랜선 n개
    val (k, n) = readln().split(" ").map { it.toInt() }

    // 랜선 길이들, 단위는 cm
    val list = Array(k) { readln().toLong() }.sortedArray()
    close()

    var max = list[list.lastIndex]
    var min = 1L
    var mid = 0L

    while (min <= max) {
        var cnt = 0L
        mid = ((max + min) / 2).toLong()

        for (i in list.indices)
            cnt += list[i] / mid

        if (cnt < n)
            max = mid - 1
        else
            min = mid + 1
    }

    println(min - 1)
}