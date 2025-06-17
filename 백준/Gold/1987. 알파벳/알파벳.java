import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		int[][] map = input();
		int result = solution(map);
		System.out.println(result);
	}

	private static final int[] dx = {-1, 1, 0, 0};
	private static final int[] dy = {0, 0, -1, 1};

	private static int[][] input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] rc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] map = new int[rc[0]][rc[1]];
		for (int i = 0; i < rc[0]; i++)
			map[i] = br.readLine().chars().map(it -> it - 'A').toArray();
		br.close();

		return map;
	}

	private static int solution(int[][] map) {
		boolean[] visited = new boolean[26];
		return back(0, 0, 1, map, visited);
	}

	private static int back(int x, int y, int cnt, int[][] map, boolean[] visited) {
		int ans = cnt;
		visited[map[x][y]] = true;

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (!isValidPosition(nx, ny, map.length, map[0].length) || visited[map[nx][ny]]) continue;

			ans = Math.max(ans, back(nx, ny, cnt + 1, map, visited));
		}

		visited[map[x][y]] = false;

		return ans;
	}

	private static boolean isValidPosition(int x, int y, int r, int c) {
		return x >= 0 && y >= 0 && x < r && y < c;
	}
}