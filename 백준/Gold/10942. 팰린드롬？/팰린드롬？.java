import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		solution();
	}
	private static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
		boolean[][] dp = dp(n, arr);
		int m = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < m; i++) {
			int[] se = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if (dp[se[0]][se[1]])
				sb.append(1).append("\n");
			else
				sb.append(0).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static boolean[][] dp(int n, int[] arr) {
		boolean[][] dp = new boolean[n + 1][n + 1];

		// 길이가 1이면 항상 true
		for (int i = 1; i <= n; i++)
			dp[i][i] = true;

		// 길이가 2일 때, 두 수가 같으면 true
		for (int i = 1; i < n; i++)
			if (arr[i] == arr[i + 1])
				dp[i][i + 1] = true;

		for (int l = 3; l <= n; l++) { // 길이
			for (int s = 1; s <= n - l + 1; s++) {
				int e = s + l - 1;
				if (arr[s] == arr[e] && dp[s + 1][e - 1])
					dp[s][e] = true;
			}
		}

		return dp;
	}
}