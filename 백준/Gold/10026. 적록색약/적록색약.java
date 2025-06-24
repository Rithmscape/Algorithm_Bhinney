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

	private static int[] solution(char[][] map) {
		int[] ans = new int[2]; // ans[0] : 적록 색약이 어난 사람, ans[1] : 적록 색약인 사람

		boolean[][] visited = new boolean[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (visited[i][j]) continue;
				dfs(i, j, map[i][j], map, visited);
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
				dfs(i, j, map[i][j], map, visited);
				ans[1]++;
			}
		}

		return ans;
	}

	private static void dfs(int x, int y, char target,  char[][] map, boolean[][] visited) {
		if (x < 0 || y < 0 || x >= map.length || y >= map.length || visited[x][y] || map[x][y] != target) return;

		visited[x][y] = true;

		dfs(x - 1, y, target, map, visited);
		dfs(x + 1, y, target, map, visited);
		dfs(x, y - 1, target, map, visited);
		dfs(x, y + 1, target, map, visited);
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
