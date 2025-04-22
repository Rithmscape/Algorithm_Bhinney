import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static final int INF = 200_000_000; // 1_000 * 200_000
	private static int n, e; // n : 정점의 개수, e : 간선의 개수
	private static int v1, v2; // 꼭 지나야하는 정점
	private static List<List<Node>> map;

	private static void solution() {
		/*
		 * 1 -> n 으로 이동
		 * case 1) 1 -> v1 -> v2 -> n
		 * case 2) 1 -> v2 -> v1 -> n
		 */

		// case 1
		int c1 = dijkstra(1, v1);
		c1 += dijkstra(v1, v2);
		c1 += dijkstra(v2, n);

		// case 2
		int c2 = dijkstra(1, v2);
		c2 += dijkstra(v2, v1);
		c2 += dijkstra(v1, n);

		int ans = (c1 >= INF && c2 >= INF) ? -1 : Math.min(c1, c2);
		System.out.println(ans);
	}
	private static int dijkstra(int s, int f) {
		int[] dist = new int[n + 1];
		Queue<Node> q = new PriorityQueue<>();

		Arrays.fill(dist, INF);
		dist[s] = 0;
		q.offer(new Node(s, 0));

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int i = 0; i < map.get(cur.node).size(); i++) {
				Node next = map.get(cur.node).get(i);
				if (cur.cost + next.cost > dist[next.node]) continue;

				dist[next.node] = cur.cost + next.cost;
				q.offer(new Node(next.node, dist[next.node]));
			}
		}

		return dist[f];
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] ne = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		n = ne[0]; e = ne[1];
		map = new ArrayList<>();

		for (int i = 0; i <= n; i++)
			map.add(new ArrayList<>());

		for (int i = 0; i < e; i++) {
			// arr[0] <-> arr[1] , 비용 : arr[2]
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			map.get(arr[0]).add(new Node(arr[1], arr[2]));
			map.get(arr[1]).add(new Node(arr[0], arr[2]));
		}

		int[] v = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		v1 = v[0]; v2 = v[1];
	}

	private static class Node implements Comparable<Node> {
		int node;
		int cost;

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node node) {
			return cost - node.cost;
		}
	}
}