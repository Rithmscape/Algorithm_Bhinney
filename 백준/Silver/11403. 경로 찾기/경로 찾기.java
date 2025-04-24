import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static int N;
	private static List<List<Integer>> map;
	private static void solution() {
		int[][] res = new int[N][N];
		for (int i = 0; i < N; i++)
			bfs(res, i); // 각 정점에서 출발하는 bfs
		Arrays.stream(res)
			.map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")))
			.forEach(System.out::println);
	}
	private static void bfs(int[][] res, int start) {
		boolean[] visited = new boolean[N];
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int n : map.get(cur)) {
				if (visited[n]) continue;

				visited[n] = true;
				res[start][n] = 1;
				q.offer(n);
			}
		}
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new ArrayList<>();
		for (int i = 0; i < N; i++)
			map.add(new ArrayList<>());
		for (int i = 0; i < N; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < N; j++)
				if (arr[j] == 1) map.get(i).add(j);
		}
		br.close();
	}
}