class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
		/*
		 * diff : 난이도
		 * time : 퍼즐 소요시간
		 * limit : 제한시간

		 if ) diff[i] <= level -> time[i] 시간만큼 소요
		 if ) diff[i] > level -> (times[i - 1] + times[i]) * (diffs[i] - level) + times[i]); 시간만큼 소요
		 min level 찾기
		 diff[0] = 1 << 무조건

		 */
		long left = 1;
		long right = limit;

		while (left < right) {
			long mid = (left + right) / 2;
			long t = time(mid, diffs, times);

			if (limit < t) left = mid + 1;
			else right = mid;
		}

		return (int) left;
	}

	private long time(long level, int[] diffs, int[] times) {
		long t = times[0];
		for (int i = 1; i < diffs.length; i++) {
			if (diffs[i] <= level) t += times[i];
			else t += ((long) (times[i - 1] + times[i]) * (diffs[i] - level) + times[i]);
		}

		return t;
	}
}