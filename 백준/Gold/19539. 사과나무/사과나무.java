import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N;
		private String answer;
		private int[] arr;

		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			answer = "YES";

			int sum = 0;
			int one = 0;
			int two = 0;

			for (int i : arr) {
				sum += i;
				one += i % 2;
				two += i / 2;
			}

			if (sum % 3 > 0 || one > two)
				answer = "NO";
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			br.close();
		}

		private void output() {
			System.out.println(answer);
		}
	}
}