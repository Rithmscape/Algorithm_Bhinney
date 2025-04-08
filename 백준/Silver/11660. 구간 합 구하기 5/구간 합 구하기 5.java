import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static int N, M;
	private static int[][] map; // 숫자 표
	private static int[][] panel; // 구해야하는 구간 [x1, y1, x2, y2], 각 좌표에 -1 필요
	private static int[][] dp;
	private static void solution() {
		for (int i = 0; i < M; i++) {
			int[] cur = panel[i]; // [x1, y1, x2, y2]

			int sum = 0;
			for (int j = cur[0]; j <= cur[2]; j++)
				sum += dp[j][cur[3]] - dp[j][cur[1] - 1];

			System.out.println(sum);
		}
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] rc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = rc[0]; // 표 크기
		M = rc[1]; // 합을 구해야하는 횟수
		map = new int[N + 1][N + 1];
		panel = new int[M][4];
		dp = new int[N + 1][N + 1];

		// 숫자 표
		for (int i = 1; i <= N; i++) {
			map[i] = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
			dp[i][0] = map[i][0];

			for (int j = 1; j <= N; j++)
				dp[i][j] = dp[i][j - 1] + map[i][j];
		}

		// 구해야하는 구간 [x1, y1, x2, y2]
		for (int i = 0; i < M; i++)
			panel[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	}
}