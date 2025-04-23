import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
	}
	private static int S = -1, F = -1;
	private static int[][] map;
	private static void solution() {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		int[][] dp = new int[map.length][map[0].length];
		boolean[][] visited = new boolean[map.length][map[0].length];
		Queue<Node> q = new LinkedList<>();
		q.offer(new Node(S, F));
		visited[S][F] = true;

		while (!q.isEmpty()) {
			Node cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur.x + dx[i];
				int ny = cur.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length || map[nx][ny] == 0 || visited[nx][ny]) continue;

				q.offer(new Node(nx, ny));
				dp[nx][ny] = dp[cur.x][cur.y] + 1;
				visited[nx][ny] = true;
			}
		}

		for (int i = 0; i < map.length; i++)
			for (int j = 0; j < map[0].length; j++)
				if (!visited[i][j] && map[i][j] != 0)
					dp[i][j] = -1;

		Arrays.stream(dp)
			.map(arr -> Arrays.stream(arr).mapToObj(String::valueOf).collect(Collectors.joining(" ")))
			.forEach(System.out::println);
	}
	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		map = new int[arr[0]][arr[1]];
		for (int i = 0; i < arr[0]; i++) {
			map[i] = Arrays.stream((br.readLine()).split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < arr[1] ; j++)
				if (S != -1 && F != -1) break;
				else if (map[i][j] == 2) {
					S = i;
					F = j;
					break;
				}
		}
		br.close();
	}
	private static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}