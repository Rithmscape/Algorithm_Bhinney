import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Main {
	public static void main(String[] args) throws IOException {
		input();
		solution();
		output();
	}

	private static int N;
	private static int[][] map;
	private static List<Integer> houses;
	private static void solution(){
		houses = new ArrayList<>();
		boolean[][] visited = new boolean[N][N];

		for(int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				if (map[i][j] == 1 && !visited[i][j]) bfs(i, j, visited);
		}
	}

	private static void bfs(int x, int y, boolean[][] visited) {
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};

		int count = 1; // 단지수 계산용
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{x, y});
		visited[x][y] = true;

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();

			for (int i = 0; i < 4; i++) {
				int nx = cur[0] + dx[i];
				int ny = cur[1] + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny] || map[nx][ny] == 0) continue;

				count++;
				visited[nx][ny] = true;
				queue.offer(new int[]{nx, ny});
			}
		}


		houses.add(count);
	}

	private static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++)
			map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		br.close();
	}

	private static void output() {
        houses.sort((o1, o2) -> o1 - o2);
		System.out.println(houses.size());

		for (int count : houses)
			System.out.println(count);
	}
}