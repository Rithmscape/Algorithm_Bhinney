import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solve();
	}

	private static class Solution {
		private void solve() throws IOException {
			String str = input();
			int answer = solution(str);
			output(answer);
		}

		private int solution(String str) {
			int n = str.length();
			int answer = operation(str.toCharArray(), n);

			for (int i = 0; i < str.length(); i++) {
				for (int j = i + 1; j < str.length(); j++) {
					char[] arr = str.toCharArray();
					arr[i] = str.charAt(j);
					arr[j] = str.charAt(i);

					answer = Math.min(answer, 1 + operation(arr, n));
				}
			}

			return answer;
		}

		private int operation(char[] arr, int n) {
			int[][] dp = new int[n][n];

			for (int len = 2; len <= n; len++) {
				for (int i = 0; i <= n - len; i++) {
					int j = i + len - 1;

					if (arr[i] == arr[j]) {
						dp[i][j] = dp[i + 1][j - 1];
					} else {
						int insert = dp[i][j - 1] + 1;
						int delete = dp[i + 1][j] + 1;
						int replace = dp[i + 1][j - 1] + 1;

						dp[i][j] = Math.min(insert, Math.min(delete, replace));
					}
				}
			}

			return dp[0][n - 1];
		}

		// --------------------------------------- 입출력 ---------------------------------------
		private String input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				return br.readLine();
			}
		}

		private void output(int answer) throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write(String.valueOf(answer));
				bw.flush();
			}
		}
	}
}