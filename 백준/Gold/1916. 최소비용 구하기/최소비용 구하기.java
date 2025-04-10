import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	private static int N, M, START, END;
	private static int[][] costs;
	private static void solution() {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		dist[START] = 0;

		Queue<Integer> q = new PriorityQueue<>();
		q.offer(START);

		while (!q.isEmpty()) {
			int cur = q.poll();

			for (int i = 1; i <= N; i++) {
				if (costs[cur][i] == Integer.MAX_VALUE || dist[i] <= dist[cur] + costs[cur][i]) continue;

				dist[i] = dist[cur] + costs[cur][i];
				q.offer(i);
			}
		}

		System.out.println(dist[END] == Integer.MAX_VALUE ? 0 : dist[END]);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 도시의 개수
		M = Integer.parseInt(br.readLine()); // 버스의 개수
		costs = new int[N + 1][N + 1];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				if (i == j)
					costs[i][j] = 0;
				else
					costs[i][j] = Integer.MAX_VALUE;
			}
		}

		for (int i = 1; i <= M; i++) {
			// arr[0]: 출발도시, arr[1]: 도착도시, arr[2]: 비용
			int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			costs[arr[0]][arr[1]] = Math.min(arr[2], costs[arr[0]][arr[1]]);
		}

		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		START = arr[0];
		END = arr[1];
	}
}
