import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Solution {
    private final int[] dx = {-1, 1, 0, 0};
	private final int[] dy = {0, 0, -1, 1};

	public int solution(int[][] board, int r, int c) {
		Map<Integer, List<int[]>> position = position(board); // 각 카드별 위치정보를 담은 map
		List<Integer> cards = new ArrayList<>(position.keySet());

		// 각 위치 별 최단 거리 계산
		return find(board, cards,  r, c, position);
	}

	private int find (int[][] board, List<Integer> cards,
		int r, int c, Map<Integer, List<int[]>> position) {
		if (cards.isEmpty())
			return 0;

		int moves = Integer.MAX_VALUE;

		for (int i = 0; i < cards.size(); i++) {
			int current = cards.get(i);
			List<int[]> positions = position.get(current);
			int[] pos1 = positions.get(0);
			int[] pos2 = positions.get(1);

			// 1. pos1 -> pos2
			int m1 = bfs(board, r, c, pos1[0], pos1[1]) + 1; // cur -> pos1
			m1 += bfs(board, pos1[0], pos1[1], pos2[0], pos2[1]) + 1; // pos1 -> pos2

			// 2. pos2 -> pos1
			int m2 = bfs(board, r, c, pos2[0], pos2[1]) + 1; // cur -> pos2
			m2 += bfs(board, pos2[0], pos2[1], pos1[0], pos1[1]) + 1; // pos2 -> pos1

			int[][] newBoard = copy(board);
			newBoard[pos1[0]][pos1[1]] = 0;
			newBoard[pos2[0]][pos2[1]] = 0;

			List<Integer> next = new ArrayList<>(cards);
			next.remove(i);

			int case1 = m1 + find(newBoard, next, pos2[0], pos2[1], position);
			int case2 = m2 + find(newBoard, next, pos1[0], pos1[1], position);

			moves = Math.min(moves, Math.min(case1, case2));
		}

		return moves;
	}

	private int bfs(int[][] board, int r1, int c1, int r2, int c2) {
		if (r1 == r2 && c1 == c2)
			return 0;

		boolean[][] visited = new boolean[4][4];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[]{r1, c1, 0});
		visited[r1][c1] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int r = cur[0];
			int c = cur[1];
			int m = cur[2];

			// 일반 이동
			for (int i = 0; i < 4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];

				if (!valid(nr, nc) || visited[nr][nc]) continue;

				if (nr == r2 && nc == c2)
					return m + 1;

				visited[nr][nc] = true;
				queue.offer(new int[]{nr, nc, m + 1});
			}

			// ctrl 이동
			for (int i = 0; i < 4; i++) {
				int nr = r + dx[i];
				int nc = c + dy[i];

				while (valid(nr, nc)) {
					if (board[nr][nc] != 0) break;
					nr += dx[i];
					nc += dy[i];
				}

				if (!valid(nr, nc)) {
					nr -= dx[i];
					nc -= dy[i];
				}

				if ((nr != r || nc != c) && !visited[nr][nc]) {
					if (nr == r2 && nc == c2)
						return m + 1;

					visited[nr][nc] = true;
					queue.offer(new int[]{nr, nc, m + 1});
				}
			}
		}

		return Integer.MAX_VALUE;
	}

	// ------------------------------------------------- helper -------------------------------------------------
	private Map<Integer, List<int[]>> position(int[][] board) {
		Map<Integer, List<int[]>> position = new HashMap<>();

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j] == 0) continue;

				position
					.computeIfAbsent(board[i][j], k -> new ArrayList<>())
					.add(new int[]{i, j});
			}
		}

		return position;
	}

	private boolean valid(int r, int c) {
		return r >= 0 && c >= 0 && r < 4 && c < 4;
	}

	private int[][] copy(int[][] board) {
		int[][] newBoard = new int[4][4];

		for (int i = 0; i < 4; i++)
			System.arraycopy(board[i], 0, newBoard[i], 0, 4);

		return newBoard;
	}
}