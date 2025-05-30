import java.util.*;

class Solution {
    private int[] oil;
	public int solution(int[][] land) {
		/*
		 * 1 -> 석유 O, 0 -> 석유 X
		 * 가로 길이만큼 반복문을 돌기
		 * 각 좌표 주변으로 탐색하면서 덩어리 크기 확인하기
		 */
		oil = new int[land[0].length]; // 각 위치별 매장량 확인
		boolean[][] visited = new boolean[land.length][land[0].length];

		for (int i = 0; i < land[0].length; i++) { // 가로
			for (int j = 0; j < land.length; j++) // 세로
				if (land[j][i] == 1 && !visited[j][i]) bfs(j, i, land, visited);
		}

		return Arrays.stream(oil).max().orElse(0);
	}

	private void bfs(int x, int y, int[][] land, boolean[][] visited) {
		int cnt = 1;

		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		visited[x][y] = true;

		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(x, y));

		Set<Integer> set = new HashSet<>(); // 오일이 있는 y 저장

		while (!q.isEmpty()) {
			Node cur = q.poll();
			set.add(cur.y);

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= land.length || ny >= land[0].length || visited[nx][ny]) continue;

				if (land[nx][ny] == 1) {
					cnt++;
					q.offer(new Node(nx, ny));
					visited[nx][ny] = true;
				}
			}
		}

		for (int num : set)
			oil[num] += cnt;
	}

	private class Node{
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}