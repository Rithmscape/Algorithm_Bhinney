class Solution {
    fun solution(my_string: String): String {
        var ans = ""
        my_string.forEachIndexed { index, ch ->
            if (ch.isUpperCase()) ans += ch.lowercaseChar()
            else ans += ch.uppercaseChar()
        }

        return ans
    }
}