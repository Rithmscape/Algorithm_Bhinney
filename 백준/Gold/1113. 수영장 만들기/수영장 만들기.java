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
		private int N, M, result;
		private int[][] pool;
		private boolean[][] visited;

		void solution() throws IOException {
			input();
			solve();
		}

		private void solve() {
			for (int k = 1; k <= 9; k++) {
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (pool[i][j] != k || visited[i][j]) continue;

						visited[i][j] = true;
						bfs(new int[]{i, j}, k);
					}
				}
			}

			System.out.println(result);
		}

		private void bfs(int[] start, int num) {
			int[] dx = {1, -1, 0, 0};
			int[] dy = {0, 0, -1, 1};

			Queue<int[]> queue = new LinkedList<>();
			queue.offer(start);

			List<int[]> list = new ArrayList<>();
			list.add(start);

			int height = Integer.MAX_VALUE;

			boolean flag = false;

			while (!queue.isEmpty()) {
				int[] cur = queue.poll();

				if (cur[0] == 0 || cur[0] == N - 1 || cur[1] == 0 || cur[1] == M - 1)
					flag = true;

				for (int i = 0; i < 4; i++) {
					int nx = cur[0] + dx[i];
					int ny = cur[1] + dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

					if (!visited[nx][ny] && pool[nx][ny] == num) {
						visited[nx][ny] = true;
						queue.offer(new int[]{nx, ny});
						list.add(new int[]{nx, ny});
					}

					if (pool[nx][ny] < num)
						flag = true;

					if (pool[nx][ny] > num)
						height = Math.min(height, pool[nx][ny]);
				}
			}

			if (!flag) {
				result += list.size() * (height - num);

				for (int[] cur : list) {
					pool[cur[0]][cur[1]] = height;
					visited[cur[0]][cur[1]] = false;
				}
			}
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			N = nm[0]; M = nm[1];
			pool = new int[N][M];
			visited = new boolean[N][M];
			result = 0;

			for (int i = 0; i < N; i++)
				pool[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

			br.close();
		}
	}
}