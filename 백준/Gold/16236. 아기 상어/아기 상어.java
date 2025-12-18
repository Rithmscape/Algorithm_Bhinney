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
		private int[][] area;
		private int[] cur;

		private final int[] dx = {-1, 1, 0, 0};
		private final int[] dy = {0, 0, -1, 1};

		void solution() throws IOException {
			input();
			solve();
		}

		private void solve() {
			int size = 2;
			int eat = 0;
			int move = 0;

			while (true) {
				PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) ->
					o1[2] != o2[2] ? Integer.compare(o1[2], o2[2]) :
						o1[0] != o2[0] ? Integer.compare(o1[0], o2[0]) :
							Integer.compare(o1[1], o2[1])));
				boolean[][] visited = new boolean[area.length][area.length];

				pq.offer(new int[] {cur[0], cur[1], 0}); // [y, x, 이동 거리]
				visited[cur[0]][cur[1]] = true;

				boolean check = false;

				while (!pq.isEmpty()) {
					cur = pq.poll();

					if (area[cur[0]][cur[1]] != 0 && area[cur[0]][cur[1]] < size) {
						area[cur[0]][cur[1]] = 0;
						eat++;
						move += cur[2];
						check = true;
						break;
					}

					for (int i = 0; i < 4; i++) {
						int nx = cur[1] + dx[i];
						int ny = cur[0] + dy[i];

						if (nx < 0 || ny < 0 || nx >= area.length || ny >= area.length || visited[ny][nx] || area[ny][nx] > size)
							continue;

						pq.offer(new int[] {ny, nx, cur[2] + 1});
						visited[ny][nx] = true;
					}
				}

				if (!check) break;

				if (size == eat) {
					size++;
					eat = 0;
				}
			}

			System.out.println(move);
		}

		private void input() throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			int n = Integer.parseInt(br.readLine());
			area = new int[n][n];

			for (int i = 0; i < n; i++) {
				area[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

				for (int j = 0; j < n; j++)
					if (area[i][j] == 9) {
						cur = new int[] {i, j};
						area[i][j] = 0;
					}
			}

			br.close();
		}
	}
}