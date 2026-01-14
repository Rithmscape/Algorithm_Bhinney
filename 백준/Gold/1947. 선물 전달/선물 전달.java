import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int MOD = 1_000_000_000;

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		sc.close();

		long[] dp = new long[1_000_001]; // n <= 1,000,000
		dp[2] = 1;

		for (int i = 3; i < n + 1; i++)
			dp[i] = (i - 1) * (dp[i - 2] + dp[i - 1]) % MOD;

		System.out.println(dp[n]);
	}
}