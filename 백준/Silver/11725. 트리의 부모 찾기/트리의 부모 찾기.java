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
		input();
		solution();
	}

	private static int N;
	private static List<List<Integer>> adj;
	private static void solution() {
		boolean[] visited = new boolean[N + 1]; // 방문 했던 노드인지 확인하기
		int[] parents = new int[N + 1]; // 부모 노드 저장용 배열
		Queue<Integer> q = new LinkedList<>();
		q.offer(1);
		visited[1] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int node : adj.get(cur)) {
				if (visited[node]) continue;

				visited[node] = true;
				q.offer(node);
				parents[node] = cur;
			}
		}

		for (int i = 2; i <= N; i++)
			System.out.println(parents[i]);
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		adj = new ArrayList<>();

		for (int i = 1; i <= N + 1; i++)
			adj.add(new ArrayList<>());

		for (int i = 0; i < N - 1; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			adj.get(arr[0]).add(arr[1]);
			adj.get(arr[1]).add(arr[0]);
		}
		br.close();
	}
}