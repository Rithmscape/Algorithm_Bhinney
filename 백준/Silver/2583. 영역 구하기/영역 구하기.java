import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		new Solution().solve();
	}

	private static class Solution {
		private int rows, cols;
		private boolean[][] blocked;

		private final int[] dr = {-1, 1, 0, 0};
		private final int[] dc = {0, 0, -1, 1};

		public void solve() throws IOException {
			input();
			List<Integer> result = solution();
			output(result);
		}

		private List<Integer> solution() {
			List<Integer> sizes = new ArrayList<>();
			boolean[][] visited = new boolean[blocked.length][blocked[0].length];
			for (int i = 0; i < blocked.length; i++)
				for (int j = 0; j < blocked[0].length; j++)
					if (!visited[i][j] && !blocked[i][j]) {
						int size = dfs(i, j, visited);
						sizes.add(size);
					}

			Collections.sort(sizes);

			return sizes;
		}

		private int dfs(int r, int c, boolean[][] check) {
			if (r < 0 || c < 0 || r >= rows || c >= cols || blocked[r][c] || check[r][c]) {
				return 0;
			}

			check[r][c] = true;
			int size = 1;

			for (int i = 0; i < 4; i++)
				size += dfs(r + dr[i], c + dc[i], check);

			return size;
		}

		private void input() throws IOException {
			try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				String[] arr = br.readLine().split(" ");
				rows = Integer.parseInt(arr[0]);
				cols = Integer.parseInt(arr[1]);
				int k = Integer.parseInt(arr[2]);

				blocked = new boolean[rows][cols];

				for (int i = 0; i < k; i++) {
					//[x1, y1, x2, y2]
					int[] pos = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

					for (int r = pos[1]; r < pos[3]; r++)
						for (int c = pos[0]; c < pos[2]; c++) {
							blocked[r][c] = true;
						}
				}
			}
		}

		private void output(List<Integer> result) throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				bw.write(String.valueOf(result.size()));
				bw.newLine();
				bw.write(result.stream().map(String::valueOf).collect(Collectors.joining(" ")));
				bw.flush();
			}
		}
	}
}