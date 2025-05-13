import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	// 인덱스로 확인하기 쉽도록 순서대로 인덱스 값으로 부여
	private static final int R = 0;
	private static final int G = 1;
	private static final int B = 2;
	private static final int INF = 1000 * 1000;

	private static int N;
	private static int[][] costs;
	private static void solution() {
		int[][] dp = new int[N][3];
		int ans = INF;

		for (int k = 0; k < 3; k++){
			for (int i = 0; i < 3; i++)
				if (i == k) dp[0][i] = costs[0][i];
				else dp[0][i] = INF;

			for (int i = 1; i < N; i++) {
				dp[i][R] = Math.min(dp[i - 1][G], dp[i - 1][B]) + costs[i][R];
				dp[i][G] = Math.min(dp[i - 1][R], dp[i - 1][B]) + costs[i][G];
				dp[i][B] = Math.min(dp[i - 1][R], dp[i - 1][G]) + costs[i][B];
			}

			for (int i = 0; i < 3; i++)
				if (i != k) ans = Math.min(ans, dp[N - 1][i]);
		}

		System.out.println(ans);
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		costs = new int[N][3];
		for (int i = 0; i < N; i++)
			costs[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		br.close();
	}
}