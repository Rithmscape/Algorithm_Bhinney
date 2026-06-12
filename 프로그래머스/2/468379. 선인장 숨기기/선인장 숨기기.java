import java.util.Arrays;

class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {

		/*
		 - m (⬇)* n (->) : 사막 격자 지도
		 - h (⬇) * w (->) : 선인장 구역 크기
		*/

		int[][] grid = new int[m][n];

		for (int[] r : grid)
			Arrays.fill(r, drops.length);

		for (int i = 0; i < drops.length; i++) {
			int r = drops[i][0];
			int c = drops[i][1];

			if (grid[r][c] == drops.length)
				grid[r][c] = i;
		}

		int left = 0, right = drops.length;
		int[] answer = new int[2];

		while (left <= right) {
			int mid = (left + right) / 2;
			int[] pos = find(m, n, h, w, grid, mid);

			if (pos != null) {
				answer = pos;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return answer;
	}

	private int[] find (int m, int n, int h, int w, int[][] grid, int k) {
		int[][] sum = new int[m + 1][n + 1];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				int v = grid[i][j] < k ? 1 : 0;
				sum[i + 1][j + 1] = v + sum[i][j + 1] + sum[i + 1][j] - sum[i][j];
			}
		}

		for (int i = h; i <= m; i++) {
			for (int j = w; j <= n; j++) {
				int s = sum[i][j] - sum[i - h][j] - sum[i][j - w] + sum[i - h][j - w];
				if (s == 0)
					return new int[]{i - h, j - w};
			}
		}

		return null;
	}
}