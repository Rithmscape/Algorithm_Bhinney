import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N, M, max;
		private int[][] graph;

		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			int[][] dp = new int[N][M];
			max = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					dp[i][j] = graph[i][j];

					if (dp[i][j] == 1 && max == 0) max = 1;
					if (i == 0 || j == 0) continue;

					if (dp[i - 1][j] > 0 && dp[i][j - 1] > 0 && dp[i - 1][j - 1] > 0 && dp[i][j] == 1) {
						dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
						max = Math.max(dp[i][j], max);
					}
				}
			}
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] arr = br.readLine().split(" ");

			N = Integer.parseInt(arr[0]);
			M = Integer.parseInt(arr[1]);

			graph = new int[N][M];

			for (int i = 0; i < N; i++)
				graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

			br.close();
		}

		private void output() {
			System.out.println(max * max);
		}
	}
}