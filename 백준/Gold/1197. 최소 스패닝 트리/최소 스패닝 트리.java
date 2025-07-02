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
		List<List<Node>> input = input();
		int answer = solution(input);
		output(answer);
	}

	private static int solution(List<List<Node>> list) {
		int answer = 0;
		boolean[] visited = new boolean[list.size()];
		Queue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(1, 0));

		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			int to = cur.to;
			int cost = cur.cost;

			if (visited[to]) continue;

			visited[to] = true;
			answer += cost;

			for (Node next : list.get(to)) {
				if (visited[next.to])  continue;
				pq.offer(next);
			}
		}

		return answer;
	}

	private static List<List<Node>> input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] ve = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		List<List<Node>> list = new ArrayList<>();

		for (int i = 0; i <= ve[0]; i++)
			list.add(new ArrayList<>());

		for (int i = 0; i < ve[1]; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			list.get(arr[0]).add(new Node(arr[1], arr[2]));
			list.get(arr[1]).add(new Node(arr[0], arr[2]));
		}

		return list;
	}

	private static void output(int answer) {
		System.out.println(answer);
	}

	private static class Node implements Comparable<Node> {
		int to;
		int cost;

		public Node(int to, int cost) {
			super();
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}