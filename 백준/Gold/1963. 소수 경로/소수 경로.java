import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private List<Convert> list;
		private boolean[] prime;
		private StringBuilder answer;

		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve () {
			prime();
			
			answer = new StringBuilder();

			for (Convert c : list) {
				int res = bfs(c.start, c.target);
				answer.append(res == -1 ? "Impossible" : res).append("\n");
			}
		}

		private int bfs (int start, int target) {
			if (start == target) return 0;

			Queue<Integer> q = new LinkedList<>();
			boolean[] visited = new boolean[10000];

			q.offer(start);
			visited[start] = true;
			int depth = 0;

			while (!q.isEmpty()) {
				int size = q.size();
				depth++;

				for (int s = 0; s < size; s++) {
					int cur = q.poll();

					for (int i = 0; i < 4; i++) {
						String numStr = String.valueOf(cur);
						char original = numStr.charAt(i);

						for (char digit = '0'; digit <= '9'; digit++) {
							if (i == 0 && digit == '0') continue;
							if (digit == original) continue;

							int next = change(numStr, i, digit);

							if (next == target) return depth;

							if (!prime[next] && !visited[next]) {
								q.offer(next);
								visited[next] = true;
							}
						}
					}
				}
			}

			return -1;
		}

		private int change(String num, int pos, char digit) {
			char[] arr = num.toCharArray();
			arr[pos] = digit;
			return Integer.parseInt(new String(arr));
		}

		private void prime () {
			prime = new boolean[10000];

			prime[0] = prime[1] = true;

			// false가 소수
			for (int i = 3; i < 10000; i++) {
				if (i % 2 == 0) {
					prime[i] = true;
					continue;
				}

				if (!prime[i]) {
					for (int j = i + i; j < 10000; j += i) {
						prime[j] = true;
					}
				}
			}
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			int tc = Integer.parseInt(br.readLine());
			list = new ArrayList<>();

			while (tc-- > 0) {
				String[] arr = br.readLine().split(" ");
				list.add(new Convert(Integer.parseInt(arr[0]), Integer.parseInt(arr[1])));
			}

			br.close();
		}

		private void output() {
			System.out.println(answer.toString());
		}
	}

	private static class Convert {
		int start;
		int target;

		public Convert(int start, int target) {
			this.start = start;
			this.target = target;
		}
	}
}