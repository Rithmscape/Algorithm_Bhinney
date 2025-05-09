import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static char[] arr1;
	private static char[] arr2;

	private static void solution() {
		int[][] dp = new int[arr1.length + 1][arr2.length + 1];

		for (int i = 1; i <= arr1.length; i++) {
			for (int j = 1; j <= arr2.length; j++) {
				if (arr1[i - 1] == arr2[j -1])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}

		System.out.println(dp[arr1.length][arr2.length]);
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr1 = br.readLine().toCharArray();
		arr2 = br.readLine().toCharArray();
		br.close();
	}
}