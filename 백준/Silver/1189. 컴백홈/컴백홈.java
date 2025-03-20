import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int ans = 0;
	static int R, C, K;
	static char[][] roads;
	static boolean[][] visited;

	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] arr = Arrays.stream(
			Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray()
			).toArray();

		R = arr[0];
		C = arr[1];
		K = arr[2];
		roads = new char[R][C];
		visited = new boolean[R][C];

		for (int i = 0; i < R; i++)
			roads[i] = br.readLine().toCharArray();
		br.close();

		visited[R - 1][0] = true;
		solution(R - 1, 0, 1);
		System.out.println(ans);
	}

	// dfs 를 사용할 것
	private static void solution(int x, int y, int m) {
		if (x == 0 && y == C - 1){
			if (m == K) ans++;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || ny < 0 || nx >= R || ny >= C || visited[nx][ny]|| roads[nx][ny] == 'T') continue;

			visited[nx][ny] = true;
			solution(nx, ny, m + 1);
			visited[nx][ny] = false;
		}
	}
}