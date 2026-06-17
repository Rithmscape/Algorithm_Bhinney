class Solution {
    public int[] solution(int target) {
        /*
		  i : 점수
		  dp[i][0] : 던진 횟수
		  dp[i][1] : 싱글 / 볼 맞힌 횟수
		 */
		int[][] dp = new int[target + 1][2];
		for (int i = 1; i <= target; i++)
			dp[i][0] = Integer.MAX_VALUE;

		for (int i = 1; i <= target; i++) {
			for (int j = 1; j <= 20; j++) {

				/* 볼을 쏘는 경우 */
				if (i - 50 >= 0) {
					if(dp[i][0] > dp[i - 50][0] + 1){
						dp[i][0] = dp[i - 50][0] + 1;
						dp[i][1] = dp[i - 50][1] + 1;
					} else if(dp[i][0] == dp[i - 50][0] + 1){
						dp[i][1] = Math.max(dp[i][1], dp[i - 50][1] + 1);
					}
				}

				/* 싱글을 쏘는 경우 */
				if (i - j >= 0) {
					if (dp[i][0] > dp[i - j][0] + 1) {
						dp[i][0] = dp[i - j][0] + 1;
						dp[i][1] = dp[i - j][1] + 1;
					} else if (dp[i][0] == dp[i - j][0] + 1) {
						dp[i][1] = Math.max(dp[i][1], dp[i - j][1] + 1);
					}
				}

				/* 더블을 쏘는 경우 */
				if ((i - (j * 2) >= 0) && (dp[i][0] > dp[i - (j * 2)][0] + 1)) {
					dp[i][0] = dp[i - (j * 2)][0] + 1;
					dp[i][1] = dp[i - (j * 2)][1];
				}

				/* 트리플을 쏘는 경우 */
				if ((i - (j * 3) >= 0) && (dp[i][0] > dp[i - (j * 3)][0] + 1)) {
					dp[i][0] = dp[i - (j * 3)][0] + 1;
					dp[i][1] = dp[i - (j * 3)][1];
				}
			}
		}

		return new int[]{dp[target][0], dp[target][1]};
    }
}