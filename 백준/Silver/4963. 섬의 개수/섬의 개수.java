import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		List<int[][]> inputs = input();
		String answer = solution(inputs);
		output(answer);
	}

	private static String solution(List<int[][]> inputs) {
		int[] answer = new int[inputs.size()];
		for (int i = 0; i < inputs.size(); i++) {
			int[][] map = inputs.get(i);
			boolean[][] visited = new boolean[map.length][map[0].length];

			for (int x = 0; x < map.length; x++)
				for (int y = 0; y < map[0].length; y++) {
					if (visited[x][y] || map[x][y] == 0) continue;
					dfs(x, y, map, visited);
					answer[i]++;
				}
		}

		return IntStream.of(answer).mapToObj(String::valueOf).collect(Collectors.joining("\n"));
	}

	private static void dfs(int x, int y, int[][] map, boolean[][] visited) {
		if (x < 0 || y < 0 || x >= map.length || y >= map[0].length || visited[x][y] || map[x][y] == 0) return;

		visited[x][y] = true;

		// 상 하 좌 우
		dfs(x - 1, y, map, visited); // 상
		dfs(x + 1, y, map, visited); // 하
		dfs(x, y - 1, map, visited); // 좌
		dfs(x, y + 1, map, visited); // 우

		// 대각선
		dfs(x - 1, y - 1, map, visited);
		dfs(x + 1, y + 1, map, visited);
		dfs(x - 1, y + 1, map, visited);
		dfs(x + 1, y - 1, map, visited);
	}

	private static List<int[][]> input() throws IOException {
		List<int[][]> list = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			int[] rc = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int r = rc[1];
			int c = rc[0];

			if (r == 0 && c == 0) break;

			int[][] map = new int[r][c];
			for (int i = 0; i < r; i++)
				map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			list.add(map);
		}

		return list;
	}

	private static void output(String answer) {
		System.out.println(answer);
	}
}
