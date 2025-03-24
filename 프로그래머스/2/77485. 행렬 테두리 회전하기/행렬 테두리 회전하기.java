class Solution {
    static int[][] map;
	public static int[] solution(int rows, int columns, int[][] queries) {
		init(rows, columns);
		int[] ans = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			ans[i] = move(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
		}
		return ans;
	}

	private static int move(int x1, int y1, int x2, int y2) { // 2, 2, 5, 4
		int num = map[x1][y2]; // 마지막 숫자는 기억 필요
		int min = num; // 바뀐 수 중에 가장 작은 수 리턴

		// 상측, 좌 -> 우 이동
		for (int i = y2; i > y1; i--) {
			min = Math.min(min, map[x1][i]);
			map[x1][i] = map[x1][i - 1];
		}

		// 좌측, 아래 -> 위 이동
		for (int i = x1; i < x2; i++) {
			min = Math.min(min, map[i][y1]);
			map[i][y1] = map[i + 1][y1];
		}

		// 하측, 우 -> 좌 이동
		for (int i = y1; i < y2; i++) {
			min = Math.min(min, map[x2][i]);
			map[x2][i] = map[x2][i + 1];
		}

		// 우측, 위 -> 아래 이동
		for (int i = x2; i > x1; i--) {
			min = Math.min(min, map[i][y2]);
			map[i][y2] = map[i - 1][y2];
;		}

		map[x1 + 1][y2] = num;

		return min;
	}

	private static void init(int r, int c) {
		map = new int[r + 1][c + 1];

		int num = 1;
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++)
				map[i][j] = num++;
		}
	}
}