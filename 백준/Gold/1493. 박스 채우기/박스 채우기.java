import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int len, wid, hei;
		private int[] cubes;

		void solution() throws IOException {
			input();
			solve();
		}

		private void solve() {
			long before = 0;
			long result = 0;
			StringBuilder answer = new StringBuilder();

			for (int i = cubes.length - 1; i >= 0; i--) {
				before <<= 3;

				long possible = (long) (len >> i) * (wid >> i) * (hei >> i) - before;
				long cube = Math.min((long) cubes[i], possible);

				before += cube;
				result += cube;
			}

			if (before == (long) len * wid * hei)
				answer.append(result).append("\n");
			else
				answer.append(-1).append("\n");

			System.out.println(answer.toString());
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String[] arr = br.readLine().split(" ");
			len = Integer.parseInt(arr[0]); wid = Integer.parseInt(arr[1]); hei = Integer.parseInt(arr[2]);

			int n = Integer.parseInt(br.readLine());
			cubes = new int[n];

			for (int i = 0; i < n; i++) {
				String[] array = br.readLine().split(" ");
				cubes[Integer.parseInt(array[0])] = Integer.parseInt(array[1]);
			}

			br.close();
		}
	}
}