import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int[][] dp = new int[10_001][4];
		dp(dp);

		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt(); // 테스트 케이스 개수

		StringBuilder sb = new StringBuilder();
		while (tc-- > 0) {
			int n = sc.nextInt();

			sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n");
		}

		System.out.println(sb.toString());
	}

	private static void dp (int[][] dp) {
		dp[1][1] = 1; // 1
		dp[2][1] = 1; // 1+1
		dp[2][2] = 1; // 2
		dp[3][1] = 1; // 1+1+1
		dp[3][2] = 1; // 1+2
		dp[3][3] = 1; // 3

		for(int i = 4; i < 10_001; i++) {
			dp[i][1] = dp[i - 1][1];
			dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
			dp[i][3] = dp[i - 3][1] + dp[i-  3][2] + dp[i - 3][3];
		}
	}
}