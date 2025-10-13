import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N, K, answer;
		private int[] values;

		private static final int MAX = Integer.MAX_VALUE - 1;

		private void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			int[] dp = new int[K + 1];

			for (int i = 1; i <= K; i++)
				dp[i] = MAX;

			for (int i = 0; i < N; i++)
				for (int j = values[i]; j <= K; j++)
					dp[j] = Math.min(dp[j], dp[j - values[i]] + 1);

			answer = dp[K] == MAX ? -1 : dp[K];
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] arr = br.readLine().split(" ");
			N = Integer.parseInt(arr[0]);
			K = Integer.parseInt(arr[1]);

			values = new int[N];
			for (int i = 0; i < N; i++)
				values[i] = Integer.parseInt(br.readLine());
		}

		private void output() {
			System.out.println(answer);
		}
	}
}