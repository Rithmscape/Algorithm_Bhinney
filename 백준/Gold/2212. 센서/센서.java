import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N, K;
		private int[] sensors;

		void solution() throws IOException {
			input();
			output(solve());
		}

		private int solve() {
			Arrays.sort(sensors);
			int[] diffs = new int[N - 1];

			for (int i = 0; i < N - 1; i++)
				diffs[i] = sensors[i + 1] - sensors[i];

			Arrays.sort(diffs);

			int sum = 0;
			for (int i = 0; i < N - K; i++)
				sum += diffs[i];

			return sum;
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			K = Integer.parseInt(br.readLine());
			sensors = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			br.close();
		}

		private void output(int answer) {
			System.out.println(answer);
		}
	}
}