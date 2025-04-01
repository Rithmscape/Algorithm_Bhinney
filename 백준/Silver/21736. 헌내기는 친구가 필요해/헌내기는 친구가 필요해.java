import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}

	static int n, m;
	static char[][] map;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	private static void solution() {
		int ans = 0;
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[n][m];

		// 도연이 위치 찾기
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				if (map[i][j] == 'I') {
					q.offer(new Node(i, j));
					visited[i][j] = true;
				}

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= m || map[nx][ny] == 'X' || visited[nx][ny]) continue;

				if (map[nx][ny] == 'P') ans++;
				q.offer(new Node(nx, ny));
				visited[nx][ny] = true;
			}
		}

		if (ans == 0) System.out.println("TT");
		else System.out.println(ans);
	}

	private static class Node{
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	// 입력 받기
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] arr = br.readLine().split(" ");
		n = Integer.parseInt(arr[0]);
		m = Integer.parseInt(arr[1]);

		map = new char[n][m];

		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();

		br.close();
	}

}