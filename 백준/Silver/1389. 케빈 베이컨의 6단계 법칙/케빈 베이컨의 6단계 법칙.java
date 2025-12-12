import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int[][] friends;
		private int idx;

		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {

			// 플로이드 와샬
			for (int k = 1; k < friends.length; k++) {
				for (int i = 1; i < friends.length; i++) {
					for (int j = 1; j < friends.length; j++) {
						if (friends[i][j] > friends[i][k] + friends[k][j]) {
							friends[i][j] = friends[i][k] + friends[k][j];
						}
					}
				}
			}

			int result = Integer.MAX_VALUE;
			idx = -1;

			// 가장 작은 idx
			for (int i = 1; i < friends.length; i++) {
				int total = 0;

				for (int j = 1; j < friends.length; j++)
					total += friends[i][j];

				if (result > total) {
					result = total;
					idx = i;
				}
			}
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			friends = new int[nm[0] + 1][nm[0] + 1];
			Arrays.stream(friends).forEach(it -> Arrays.fill(it, 999999999));

			for (int i = 1; i < friends.length; i++)
				friends[i][i] = 0;

			for (int i = 0; i < nm[1]; i++) {
				int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				friends[arr[0]][arr[1]] = friends[arr[1]][arr[0]] = 1;
			}

			br.close();
		}

		private void output() {
			System.out.println(idx);
		}
	}
}