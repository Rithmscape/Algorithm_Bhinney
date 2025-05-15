fun main() = with(System.`in`.bufferedReader()) {
    val arr = readln().split(" ")
    val month = changeMonth(arr[0].slice(0 .. 2))
    val date = arr[1].slice(0 until arr[1].lastIndex).toInt()
    val year = arr[2].toInt()
    val time = arr[3].split(":")[0].toInt() * 60 + arr[3].split(":")[1].toInt()
    solution(month, date, year, time)
}
private fun solution(month : Int, date : Int, year : Int, time : Int) {
    val days = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val checkYear = (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) // 윤년 확인
    if (checkYear) days[2] = 29

    var totalDay = date - 1
    for (i in 1 until  month)
        totalDay += days[i]

    val total = (60 * 24 * totalDay + time).toDouble()
    val totalYear = if (checkYear) (366 * 60 * 24).toDouble() else (365 * 60 * 24).toDouble()

    println(total / totalYear * 100)
}
private fun changeMonth(m : String) : Int {
    return when(m) {
        "Jan" -> 1
        "Feb" -> 2
        "Mar" -> 3
        "Apr" -> 4
        "May" -> 5
        "Jun" -> 6
        "Jul" -> 7
        "Aug" -> 8
        "Sep" -> 9
        "Oct" -> 10
        "Nov" -> 11
        "Dec" -> 12
        else -> 0
    }
}