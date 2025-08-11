import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solve();
	}

	private static class Solution {
		private int n, m;
		private boolean[][] map;
		private int count = 0;
		private int size = 0;
		List<Integer> sizes = new ArrayList<>();

		public void solve() throws IOException {
			input();
			solution();
			output();
		}

		private void solution() {
			boolean[][] visited = new boolean[map.length][map[0].length];
			for (int i = 0; i < map.length; i++)
				for (int j = 0; j < map[0].length; j++)
					if (!visited[i][j] && !map[i][j]) {
						find(i, j, visited);
						count++;
						sizes.add(size);
						size = 0;
					}
		}

		private void find(int x, int y, boolean[][] check) {
			if (x < 0 || y < 0 || x >= m || y >= n || map[x][y] || check[x][y]) return;

			check[x][y] = true;
			size++;

			find(x + 1, y, check);
			find(x - 1, y, check);
			find(x, y + 1, check);
			find(x, y - 1, check);
		}

		private void input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				int[] mnk = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				m = mnk[0];
				n = mnk[1];
				int k = mnk[2];

				map = new boolean[m][n];

				for (int i = 0; i < k; i++) {
					//[x1, y1, x2, y2]
					int[] pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

					for (int x = pos[1]; x < pos[3]; x++)
						for (int y = pos[0]; y < pos[2]; y++) {
							map[x][y] = true;
						}
				}
			}
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write(String.valueOf(count) + "\n");
				bw.write(sizes.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
				bw.flush();
			}
		}
	}
}