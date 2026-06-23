class Solution {
    public int[] solution(int e, int[] starts) {
        int[] cnt = new int[e + 1];
		for (int i = 1; i <= e; i++)
			for (int j = i; j <= e; j += i)
				cnt[j]++;

		int[] dp = new int[e + 1];
		int count = -1, num = -1; // 최댓값을 구할 것
		for (int i = e; i >= 1; i--) {
			if (cnt[i] >= count) {
				count = cnt[i];
				num = i;
			}

			dp[i] = num;
		}

		int[] answer = new int[starts.length];
		for (int i = 0; i < starts.length; i++)
			answer[i] = dp[starts[i]];

		return answer;
    }
}