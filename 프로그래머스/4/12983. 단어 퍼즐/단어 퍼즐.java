import java.util.Arrays;

class Solution {
    public int solution(String[] arr, String target) {
       int[] dp = new int[target.length() + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		for (int i = 1; i < dp.length; i++) {
			for (String str : arr) {
				int len = str.length();
				if (i >= len && target.substring(i - len, i).equals(str)) {
					if (dp[i - len] != Integer.MAX_VALUE) {
						dp[i] = Math.min(dp[i], dp[i - len] + 1);
					}
				}
			}
		}

		return dp[target.length()] == Integer.MAX_VALUE ? -1 : dp[target.length()];
    }
}