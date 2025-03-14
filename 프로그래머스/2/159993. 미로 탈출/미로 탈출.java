import java.util.LinkedList;
import java.util.Queue;

class Solution {

	private static final int[] dx = {1, 0, -1, 0};
	private static final int[] dy = {0, 1, 0, -1};

	public static int solution(String[] maps) {
		int n = maps.length;
		int m = maps[0].length();

		// maps 배열을 이차원 char 배열로 바꾸기
		char[][] miro = new char[n][m];
		for (int i = 0; i < maps.length; i++) {
			miro[i] = maps[i].toCharArray();
		}

		// 시작 -> 레버 최단거리
		Point start = locate(miro, 'S', n, m);
		int toLever = bfs(miro, n, m, 'L', start);
		if (toLever == -1) return -1;

		// 레버 -> 출구 최단거리
		Point lever = locate(miro, 'L', n, m);
		int toExit = bfs(miro, n, m, 'E', lever);
		if (toExit == -1) return -1;

		return toLever + toExit;
	}

	private static int bfs(char[][] miro, int n, int m, char target, Point start) {
		boolean[][] visited = new boolean[n][m];
		visited[start.x][start.y] = true;

		Queue<Point> q = new LinkedList<>();
		q.offer(start);

		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (isRange(nx, ny, n, m)) {
					if (miro[nx][ny] == target)
						return cur.depth + 1;

					if (miro[nx][ny] != 'X' && !visited[nx][ny]) {
						visited[nx][ny] = true;
						q.offer(new Point(nx, ny, cur.depth + 1));
					}
				}
			}
		}

		int ans = -1; // 갈 수 없다면 -1
		return ans;
	}

	private static boolean isRange(int x, int y, int n, int m) {
		return x >= 0 && y >= 0 && x < n && y < m;
	}

	private static Point locate(char[][] miro, char target, int n, int m) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (miro[i][j] == target)
					return new Point(i, j, 0);
			}
		}

		return new Point(-1, -1, -1);
	}

	private static class Point{
		int x;
		int y;
		int depth;

		public Point(int x, int y, int depth) {
			this.x = x;
			this.y = y;
			this.depth = depth;
		}
	}
}