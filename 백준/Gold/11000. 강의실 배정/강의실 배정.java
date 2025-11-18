import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N;
		private int[][] classes;
		private PriorityQueue<Integer> pq;

		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			Arrays.sort(classes, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);

			pq = new PriorityQueue<>();
			pq.offer(classes[0][1]);

			for (int i = 1; i < N; i++) {
				if (pq.peek() <= classes[i][0]) pq.poll();
				pq.offer(classes[i][1]);
			}
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());

			classes = new int[N][2];
			for (int i = 0; i < N; i++)
				classes[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

			br.close();
		}

		private void output() {
			System.out.println(pq.size());
		}
	}
}