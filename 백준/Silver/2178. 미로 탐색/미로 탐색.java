import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 * 1은 이동 가능, 0은 이동 불가능
 * 출발은 [1, 1] -> 인덱스는 [0, 0]
 * [1, 1] -> [N, M]
 * [0, 0] -> [N - 1, M - 1] 최소
 */

public class Main {
	public static void main(String[] args) throws IOException{
		input();
		solution();
		output();
	}

	private static int N, M;
	private static int[][] map;
	private static void solution() {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		boolean[][] visited = new boolean[N][M];
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[]{0, 0});
		visited[0][0] = true;

		while (!q.isEmpty()) {
			int[] cur = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0 || visited[nx][ny]) continue;

				visited[nx][ny] = true;
				map[nx][ny] = map[cur[0]][cur[1]] + 1;
				q.offer(new int[]{nx, ny});
			}
		}
	}

	private static void input() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		N = nm[0]; M = nm[1];

		map = new int[N][M];
		for (int i = 0; i < N; i++)
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();

		br.close();
	}

	private static void output() {
		System.out.println(map[N - 1][M - 1]);
	}
}