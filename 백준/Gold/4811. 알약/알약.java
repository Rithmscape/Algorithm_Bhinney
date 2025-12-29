import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] dp = new long[31];
		dp[0] = dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i < 31; i++) {
			long cnt = 0;

			for (int j = 0; j < i; j++)
				cnt += dp[j] * dp[i - j - 1];

			dp[i] = cnt;
		}

		StringBuilder sb = new StringBuilder();

		while (true) {
			int n = Integer.parseInt(br.readLine());

			if (n == 0) break;

			sb.append(dp[n]).append("\n");
		}

		br.close();

		System.out.println(sb.toString());
	}
}