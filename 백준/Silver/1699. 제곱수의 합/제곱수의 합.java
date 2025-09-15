import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		new Solution().solution();
	}

	private static class Solution {
		private int N;
		private int[] dp;
		private void solution() {
			input();
			solve();
			output();
		}

		private void solve() {
			dp = new int[N + 1];

			for (int i = 1; i < N + 1; i++)
				dp[i] = i;

			for (int i = 2; i < N + 1; i++)
				for (int j = 1; j * j < i + 1; j++)
					dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
		}

		private void input() {
			Scanner sc = new Scanner(System.in);
			N = sc.nextInt();
			sc.close();
		}

		private void output() {
			System.out.println(dp[N]);
		}
	}
}