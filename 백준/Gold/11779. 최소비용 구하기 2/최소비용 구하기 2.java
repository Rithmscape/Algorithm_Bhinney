import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solution();
	}

	private static class Solution {
		private int N, M, from, to;
		private ArrayList<ArrayList<Node>> graph;
		private int[] dist, route;
		private boolean[] visited;
		ArrayList<Integer> routes;

		void solution() throws IOException {
			input();
			solve();
			output();
		}

		private void solve() {
			init();

			PriorityQueue<Node> pq = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost));
			pq.offer(new Node(from, 0));
			dist[from] = 0;
			route[from] = 0;

			while (!pq.isEmpty()) {
				Node cur = pq.poll();

				if (visited[cur.to]) continue;

				visited[cur.to] = true;
				for (int i = 0; i < graph.get(cur.to).size(); i++) {
					Node next = graph.get(cur.to).get(i);

					if (dist[next.to] <= dist[cur.to] + next.cost) continue;

					dist[next.to] = dist[cur.to] + next.cost;
					pq.offer(new Node(next.to, dist[next.to]));
					route[next.to] = cur.to;
				}
			}

			int cur = to;
			while (cur != 0) {
				routes.add(cur);
				cur = route[cur];
			}
		}

		private void init() {
			dist = new int[N + 1];
			Arrays.fill(dist, 100_000_001);
			route = new int[N + 1];
			visited = new boolean[N + 1];
			routes = new ArrayList<>();
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			graph = new ArrayList<>();
			for (int i = 0; i < N + 1; i++)
				graph.add(new ArrayList<>());

			for (int i = 0; i < M; i++) {
				String[] arr = br.readLine().split(" ");

				graph.get(Integer.parseInt(arr[0]))
					.add(new Node(Integer.parseInt(arr[1]), Integer.parseInt(arr[2])));
			}

			String[] arr = br.readLine().split(" ");
			from = Integer.parseInt(arr[0]);
			to = Integer.parseInt(arr[1]);

			br.close();
		}

		private void output() throws IOException {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			bw.write(dist[to] + "\n");
			bw.write(routes.size() + "\n");
			for (int i = routes.size() - 1; i >= 0; i--) {
				bw.write(routes.get(i) + " ");
			}

			bw.flush();
			bw.close();
		}
	}

	private static class Node {
		int to;
		int cost;

		public Node(int to, int cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}
