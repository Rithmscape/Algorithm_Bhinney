import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int x;
		private List<List<int[]>> graph;
		private List<List<int[]>> reverse;

		void solution() throws IOException {
			input();
			solve();
		}

		private void solve() {
			int[] d1 = dijkstra(graph);
			int[] d2 = dijkstra(reverse);

			int answer = 0;
			for (int i = 1; i < d2.length; i++) {
				answer = Math.max(answer, d1[i] + d2[i]);
			}

			System.out.println(answer);
 		}

		private int[] dijkstra(List<List<int[]>> g) {
			PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
			pq.offer(new int[] {x, 0});

			boolean[] check = new boolean[g.size()];
			int[] dist = new int[g.size()];

			Arrays.fill(dist, 987654321);
			dist[x] = 0;

			while (!pq.isEmpty()) {
				int[] cur = pq.poll();

				if (check[cur[0]]) continue;

				check[cur[0]] = true;

				for (int[] arr : g.get(cur[0])) {
					if (check[arr[0]] || dist[arr[0]] <= dist[cur[0]] + arr[1]) continue;

					dist[arr[0]] = dist[cur[0]] + arr[1];
					pq.offer(new int[] {arr[0], dist[arr[0]]});
				}
			}

			return dist;
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			// [n, m, x]
			int[] nmx = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			x = nmx[2];

			graph = new ArrayList<>();
			reverse = new ArrayList<>();
			for (int i = 0; i <= nmx[0]; i++) {
				graph.add(new ArrayList<>());
				reverse.add(new ArrayList<>());
			}

			for (int i = 0; i < nmx[1]; i++) {
				// 시작 끝 소요시간
				int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

				graph.get(arr[0]).add(new int[] {arr[1], arr[2]}); // [end, time]
				reverse.get(arr[1]).add(new int[] {arr[0], arr[2]}); // [start, time]
			}
		}
	}
}