import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
		/*
		 * A의 흔적 < n
		 * B의 흔적 < m
		 * info[i] = {a 흔적, b 흔적}
		 */

		int[][] dp = new int[info.length + 1][m];
		Arrays.stream(dp).forEach(it -> Arrays.fill(it, -1)); // 불가능한 경우인 -1로 채워놓고 시작
		dp[0][0] = 0;

		for (int i = 0; i < info.length; i++) {
			int a = info[i][0];
			int b = info[i][1];

			for (int j = 0; j < m; j++) {
				if (dp[i][j] == -1) continue;

				// 1. A가 훔칠 때
				int new_a = dp[i][j] + a;
				if (new_a < n) { // 훔칠 수 O
					if (dp[i + 1][j] == -1 || dp[i + 1][j] > new_a)
						dp[i + 1][j] = new_a;
				}

				// 2. B가 훔칠 때
				int new_b = j + b;
				if (new_b < m) { // 훔칠 수 O
					if (dp[i + 1][new_b] == -1 || dp[i + 1][new_b] > dp[i][j])
						dp[i + 1][new_b] = dp[i][j];
				}
			}
		}

		return Arrays.stream(dp[info.length])
			.filter(it -> it != -1)
			.min()
			.orElse(-1);
	}
}