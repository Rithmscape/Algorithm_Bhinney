class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] =true;

		for (int j = 2; j <= p.length(); j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j-2];
			}
		}

		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				char sChar = s.charAt(i-1);
				char pChar = p.charAt(j-1);

				if (sChar == pChar || pChar =='.') {
					dp[i][j] = dp[i-1][j-1];
				} else if (pChar == '*') {
					dp[i][j] = dp[i][j - 2];

					char pPrevChar = p.charAt(j - 2);
					if (pPrevChar == sChar || pPrevChar == '.') {
						dp[i][j] |= dp[i - 1][j];
					}
				}
			}
		}

		return dp[s.length()][p.length()];
    }
}