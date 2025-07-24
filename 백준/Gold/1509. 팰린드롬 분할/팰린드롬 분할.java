import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		char[] str = input();
		int answer = solution(str);
		output(answer);
	}

	private static int solution(char[] arr) {
		int len = arr.length;
		boolean[][] check = new boolean[len + 1][len + 1];
		for (int i = 1; i < len + 1; i++) // 길이가 1이면 항상 true
			check[i][i] = true;

		int[] dp = new int[len + 1];
		dp[1] = 1;
		for (int i = 2; i < len + 1; i++) {
			int min = dp[i - 1] + 1;

			for (int j = 1; j < i; j++) {
				if (arr[j - 1] == arr[i - 1]) {
					if (j + 1 == i || check[j + 1][i - 1]) {
						check[j][i] = true;
						min = Math.min(min, dp[j - 1] + 1);
					}
				}
			}

			dp[i] = min;
		}

		return dp[len];
	}

	private static char[] input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine().trim();
		br.close();

		return s.toCharArray();
	}

	private static void output(int answer) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
	}
}