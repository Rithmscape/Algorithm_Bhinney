import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N;
		private long[] answer, liquids;
		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			answer = new long[3];
			Arrays.sort(liquids);

			long min = Long.MAX_VALUE;

			for (int i = 0; i < N - 2; i++) {
				int left = i + 1;
				int right = N - 1;

				while (left < right) {
					long sum = liquids[i] + liquids[left] + liquids[right];
					long abs = Math.abs(sum);

					if (abs < min) {
						min = abs;
						answer[0] = liquids[i];
						answer[1] = liquids[left];
						answer[2] = liquids[right];
					}

					if (sum == 0) return;
					else if (sum < 0) left++;
					else right--;
				}
			}
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			liquids = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
			br.close();
		}

		private void output() {
			System.out.println(Arrays.stream(answer).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
		}
	}
}
