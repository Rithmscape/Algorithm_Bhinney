import java.util.*;

class Solution {
    private static final int[] dx = {-1, 1, 0, 0};
	private static final int[] dy = {0, 0, -1, 1};

	public static int solution(String[] board) {
		char[][] boards = new char[board.length][board[0].length()];

		for (int i = 0; i < boards.length; i++) {
			boards[i] = board[i].toCharArray();
		}

		return bfs(boards, board.length, board[0].length());
	}

	private static int bfs(char[][] boards, int n, int m) {
		int[][] visited = new int[n][m];
		Queue<Point> queue = new ArrayDeque<>();

		// queue 에 시작점 담기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (boards[i][j] == 'R') {
					queue.offer(new Point(i, j));
					visited[i][j] = 1;
					break;
				}
			}
		}

		// 만약 도달할 수 없는 경우 -1 반환
		int ans = -1;

		while (!queue.isEmpty()) {
			Point cur = queue.poll();

			// 만약 도착 지점이라면
			if (boards[cur.x][cur.y] == 'G') {
				ans = visited[cur.x][cur.y] - 1;
				break;
			}

			// 도착 지점이 아니라면, 상 하 좌 우 확인 필요
			for (int a = 0; a < 4; a++) {
				int nx = cur.x + dx[a];
				int ny = cur.y + dy[a];

				// 최대한 이동 다 해보기
				while (true) {
					if (nx >= 0 && nx < n && ny >= 0 && ny < m && boards[nx][ny] != 'D') {
						nx += dx[a];
						ny += dy[a];
					} else {
						nx -= dx[a];
						ny -= dy[a];

						break;
					}
				}

				if (visited[nx][ny] == 0) {
					queue.add(new Point(nx, ny));
					visited[nx][ny] = visited[cur.x][cur.y] + 1;
				}
			}
		}

		return ans;
	}

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}