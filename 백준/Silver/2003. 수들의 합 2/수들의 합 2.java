import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N, M;
		private long[] arr;
		private long count;

		private void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			int left = 0, right = 0;
			long sum = 0;
			count = 0;

			while (right < N) {
				if (sum < M) {
					sum += arr[right];
					right++;
				} else if (sum == M) {
					count++;
					sum -= arr[left];
					left++;
				} else {
					sum -= arr[left];
					left++;
				}
			}

			while (left < N && sum >= M) {
				if (sum == M)
					count++;
				
				sum -= arr[left];
				left++;
			}
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N = nm[0]; M = nm[1];
			arr = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
		}

		private void output() throws IOException {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			bw.write(String.valueOf(count));
			bw.flush();
		}
	}
}