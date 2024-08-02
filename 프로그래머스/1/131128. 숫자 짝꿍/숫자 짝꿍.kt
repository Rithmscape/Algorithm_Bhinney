import kotlin.math.max
import kotlin.math.min

class Solution {
    fun solution(X: String, Y: String): String {
       val x = IntArray (10)
        val y = IntArray (10)

        X.forEach { x[it.digitToInt()]++ }
        Y.forEach { y[it.digitToInt()]++ }

        val ans = StringBuilder()
        for (i in 9 downTo 0)
            ans.append(i.toString().repeat(max(0, min(x[i], y[i]))))

        if (ans.isEmpty()) return "-1"
        if (ans.startsWith("0")) return "0"

        return ans.toString()
    }
}