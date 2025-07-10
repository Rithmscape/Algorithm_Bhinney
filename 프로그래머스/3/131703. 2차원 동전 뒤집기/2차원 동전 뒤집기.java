import java.util.Arrays;

class Solution {
    public int solution(int[][] beginning, int[][] target) {
		int r = beginning.length;
		int c = beginning[0].length;
		int flips = Integer.MAX_VALUE;

		for (int rows = 0; rows < (1 << r); rows++) {
			int[][] cur = Arrays.stream(beginning)
				.map(int[]::clone)
				.toArray(int[][]::new);

			int cnt = 0;

			for (int i = 0; i < r; i++) {
				if ((rows & (1 << i)) != 0) {
					flipRow(cur, i);
					cnt++;
				}
			}

			boolean possible = true;
			for (int j = 0; j < c; j++) {
				boolean need = (cur[0][j] != target[0][j]);

				if (need) {
					flipCol(cur, j);
					cnt++;
				}

				for (int i = 0; i < r; i++) {
					if (cur[i][j] != target[i][j]) {
						possible = false;
						break;
					}
				}

				if (!possible) break;
			}

			if (possible) flips = Math.min(flips, cnt);
		}

		return flips == Integer.MAX_VALUE ? -1 : flips;
	}

	private void flipRow(int[][] arr, int row) {
		for (int j = 0; j < arr[row].length; j++)
			arr[row][j] = 1 - arr[row][j];
	}

	private void flipCol(int[][] arr, int col) {
		for (int i = 0; i < arr.length; i++) {
			arr[i][col] = 1 - arr[i][col];
		}
	}
}