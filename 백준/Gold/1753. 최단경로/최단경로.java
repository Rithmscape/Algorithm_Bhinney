import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	// V : 정점의 개수, E : 간선의 개수, K : 시작하는 정점
	private static int V, E, K;
	private static ArrayList<ArrayList<Node>> list;
	private static void solution() {
		int[] dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		boolean[] visited = new boolean[V + 1];
		Queue<Node> q = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
		dist[K] = 0;
		q.offer(new Node(K, 0));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			if (visited[cur.v]) continue;

			visited[cur.v] = true;
			for (int i = 0; i < list.get(cur.v).size(); i++) {
				Node next = list.get(cur.v).get(i);

				if (!visited[next.v] && cur.w + next.w < dist[next.v]) {
					dist[next.v] = cur.w + next.w;
					q.offer(new Node(next.v, dist[next.v]));
				}
			}
		}

		for (int i = 1; i <= V; i++) {
			System.out.println(dist[i] == Integer.MAX_VALUE ? "INF" : dist[i]);
		}
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] ve = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		V = ve[0];
		E = ve[1];
		K = Integer.parseInt(br.readLine());

		list = new ArrayList<>();
		for (int i = 0; i <= V; i++)
			list.add(new ArrayList<>());
		// (u, v, w) -> (u에서 v로 가는 가중치 w)
		for (int i = 0; i < E; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			list.get(arr[0]).add(new Node(arr[1], arr[2]));
		}
		br.close();
	}
	private static class Node {
		int v;
		int w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}