import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		char[][] map = input();
		int[] ans = solution(map);
		output(ans);
	}

	private final static int[] dx = {-1, 1, 0, 0};
	private final static int[] dy = {0, 0, -1, 1};

	private static int[] solution(char[][] map) {
		int[] ans = new int[2]; // ans[0] : 적록 색약이 어난 사람, ans[1] : 적록 색약인 사람

		boolean[][] visited = new boolean[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (visited[i][j]) continue;
				bfs(i, j, map[i][j], map, visited);
				ans[0]++;
			}
		}

		Arrays.stream(map)
			.forEach(it -> {
				for (int i= 0; i < it.length; i++)
					if (it[i] == 'G') it[i] = 'R';
			});

		visited = new boolean[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (visited[i][j]) continue;
				bfs(i, j, map[i][j], map, visited);
				ans[1]++;
			}
		}

		return ans;
	}

	private static void bfs(int x, int y, char target,  char[][] map, boolean[][] visited) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{x, y});
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= map.length || ny >= map.length ||
					visited[nx][ny] || map[nx][ny] != target) continue;

				visited[nx][ny] = true;
				queue.offer(new int[]{nx, ny});
			}
		}
	}

	private static char[][] input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		char[][] map = new char[n][n];

		for (int i = 0; i < n; i++)
			map[i] = br.readLine().toCharArray();

		return map;
	}

	private static void output(int[] ans) {
		System.out.println(ans[0] + " " + ans[1]);
	}
}
