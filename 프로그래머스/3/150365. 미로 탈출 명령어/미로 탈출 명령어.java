class Solution {
    private static final String IMPOSSIBLE = "impossible";

	// 사전 순으로 움직이기 나열
	private static int[] dx = {1, 0, 0, -1};
	private static int[] dy = {0, -1, 1, 0};
	private static String[] directions = {"d", "l", "r", "u"};

	private static String res;
	public static String solution(int n, int m, int x, int y, int r, int c, int k) {
		res = null;

		// 맨해튼 거리 계산
		int distance = Math.abs(x - r) + Math.abs(y - c);

		// 불가능한 경우
		if (distance> k || (k - distance) % 2 != 0) return IMPOSSIBLE;

		dfs(n, m, x, y, r, c, k, 0, "");

		return res == null ? IMPOSSIBLE : res;
	}

	private static void dfs(int n, int m, int x, int y, int r, int c, int k, int depth, String path) {
		if (res != null) return;

		int remain = k - depth;
		int distance = Math.abs(x - r) + Math.abs(y - c);

		if (distance > remain || (remain - distance) % 2 != 0) return;

		if (x == r && y == c && depth == k) {
			res = path;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx <= 0 || ny <= 0 || nx > n || ny > m) continue;

			dfs(n, m, nx, ny, r, c, k, depth + 1, path + directions[i]);
		}
	}
}