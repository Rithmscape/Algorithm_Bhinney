class Solution {
    fun solution(schedules: IntArray, timelogs: Array<IntArray>, startday: Int): Int {
        var answer: Int = 0
        for (i in schedules.indices)
            if (employee(change(schedules[i].toString()), timelogs[i], startday))
                answer++

        return answer
    }

    private fun employee(schedule : Int, log : IntArray, start : Int) : Boolean {
        var day = start

        for (i in log.indices)
            if (day % 7 == 0 || day % 7 == 6)
                day++
            else {
                val time = change(log[i].toString())
                if (time > schedule + 10) return false
                day++
            }

        return true
    }

    private fun change(time : String)
    = time.substring(0, time.length - 2).toInt() * 60 + time.substring(time.length - 2).toInt()
}