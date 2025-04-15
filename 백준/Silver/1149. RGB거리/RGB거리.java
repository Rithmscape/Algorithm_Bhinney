import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static final int R = 0;
	private static final int G = 1;
	private static final int B = 2;

	private static int N;
	private static int[][] cost;
	private static void solution() {
		int[][] dp = new int[N][3];
		dp[0][R] = cost[0][R];
		dp[0][G] = cost[0][G];
		dp[0][B] = cost[0][B];

		int r = dfs(dp, N - 1, R);
		int g = dfs(dp, N - 1, G);
		int b = dfs(dp, N - 1, B);

		System.out.println(Math.min(r, Math.min(g, b)));
	}
	private static int dfs(int[][] dp, int n, int color) {
		if (dp[n][color] != 0)
			return dp[n][color];

		switch (color) {
			case R :
				dp[n][R] = Math.min(dfs(dp, n - 1, G), dfs(dp, n - 1, B)) + cost[n][R];
				break;
			case G :
				dp[n][G] = Math.min(dfs(dp, n - 1, R), dfs(dp, n - 1, B)) + cost[n][G];
				break;
			case B :
				dp[n][B] = Math.min(dfs(dp, n - 1, R), dfs(dp, n - 1, G)) + cost[n][B];
				break;
		}

		return dp[n][color];
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		cost = new int[N][3];

		for (int i = 0; i < N; i++) {
			cost[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		br.close();
	}
}