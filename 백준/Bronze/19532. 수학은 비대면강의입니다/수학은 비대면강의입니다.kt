fun main() = with(System.`in`.bufferedReader()) {

    // a, b, c, d, e, f
    val arr = readln().split(" ").map(String::toInt)

    // ax + by = c
    // dx + ey = f

    val res  = intArrayOf(0, 0)
    loop@ for (x in -999 .. 999)
        for (y in -999 .. 999)
            if ((arr[0] * x + arr[1] * y == arr[2]) && (arr[3] * x + arr[4] * y == arr[5])){
                res[0] = x
                res[1] = y
                break@loop
            }

    print(res.joinToString(" "))
}