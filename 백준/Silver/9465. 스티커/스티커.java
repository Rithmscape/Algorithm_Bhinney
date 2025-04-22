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
		int t = Integer.parseInt(br.readLine()); // 테스트 케이스

		for (int i = 0; i < t; i++) {
			int n = Integer.parseInt(br.readLine());
			int [][] stickers = new int[2][n + 1];
			stickers[0] = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
			stickers[1] = Arrays.stream(("0 " + br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();

			int [][] dp = new int[2][n + 1];
			dp[0][1] = stickers[0][1];
			dp[1][1] = stickers[1][1];

			for (int j = 2; j <= n; j++) {
				dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + stickers[0][j];
				dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + stickers[1][j];
			}

			System.out.println(Math.max(dp[0][n], dp[1][n]));
		}

		br.close();
	}
}