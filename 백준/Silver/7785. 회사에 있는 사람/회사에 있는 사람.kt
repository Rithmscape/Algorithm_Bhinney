fun main() = with(System.`in`.bufferedReader()) {
    val n = readln().toInt()
    val arr = HashSet<String>()
    repeat(n) {
        val (name, status) = readln().split(" ")
        if (status == "enter") arr.add(name)
        else arr.remove(name)
    }


    arr.sortedDescending().forEach { println(it) }
}