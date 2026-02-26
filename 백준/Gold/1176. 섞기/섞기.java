import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int n, k;
		private int[] students;

		void solution() throws IOException {
			input();
			solve();
		}

		private void solve() {
			long[][] dp = new long[n + 1][(int) Math.pow(2, n + 1)];
			rec(0, 1, dp);
			System.out.println(dp[0][1]);
		}

		private long rec(int last, int status, long[][] dp) {
			if (status == Math.pow(2, n + 1) - 1) return 1;
			if (dp[last][status] != 0) return dp[last][status];

			for (int i = 1; i <= n; i++) {
				if(((status & 1 << i) == 0) && (last == 0 || Math.abs(students[last] - students[i]) > k))
					dp[last][status] += rec(i, status | 1 << i, dp);
			}

			return dp[last][status];
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String nk = br.readLine();
			n = Integer.parseInt(nk.split(" ")[0]);
			k = Integer.parseInt(nk.split(" ")[1]);

			students = new int[n + 1];
			for (int i = 1; i <= n; i++)
				students[i] = Integer.parseInt(br.readLine());

			br.close();
		}
	}
}