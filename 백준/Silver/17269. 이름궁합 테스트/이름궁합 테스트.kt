fun main() = with(System.`in`.bufferedReader()) {

    // 입력 받기
    val (n, m) = readln().split(" ").map { it.toInt() }
    val (a, b) = readln().split(" ")

    // 획수 저장해놓기
    val counts = intArrayOf(3, 2, 1, 2, 4, 3, 1, 3, 1, 1, 3, 1, 3, 2, 1, 2, 2, 2, 1, 2, 1, 1, 1, 2, 2, 1)

    // 초기 상태 만들기
    val init = mutableListOf<Int>()

    for (i in 0 until maxOf(n, m)) {
        if (i < n)
            init.add(counts[a[i] - 'A'])

        if (i < m)
            init.add(counts[b[i] - 'A'])
    }

    // 인접한 수를 더하기
    var merge = init
    while (merge.size > 2) {
        val next = mutableListOf<Int>()

        for (i in 0 until merge.size - 1)
            next.add((merge[i] + merge[i + 1]) % 10)

        merge = next
    }

    // 최종 확률 출력
    val result = if (merge[0] == 0) "${merge[1]}%" else "${merge[0]}${merge[1]}%"
    print(result)
}