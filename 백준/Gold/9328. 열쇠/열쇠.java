import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
	public static void main(String[] args) throws IOException {
		new StealDocuments().solve();
	}

	private static class StealDocuments {
		private List<char[][]> buildings;
		private List<Set<Character>> keys;
		private int[] answer;

		private final int[] dx = {-1, 1, 0, 0};
		private final int[] dy = {0, 0, -1, 1};

		public void solve() throws IOException {
			input();
			steal();
			output();
		}

		private void steal() {
			/*
			 * 대문자 : 문
			 * 소문자 : 열쇠
			 */

			answer = new int[keys.size()];

			for (int i = 0; i < answer.length; i++) {
				char[][] map = buildings.get(i);
				Set<Character> key = keys.get(i);

				while (true) {
					boolean[][] visited = new boolean[map.length][map[0].length];
					Queue<int[]> queue = new LinkedList<>();
					int documents = 0;
					boolean newKey= false;

					for (int r = 0; r < map.length; r++) {
						for (int c = 0; c < map[0].length; c++) {
							if ((r == 0 || r == map.length - 1 || c == 0 || c == map[0].length - 1) && pass(map[r][c], key)) {
								visited[r][c] = true;
								queue.offer(new int[]{r, c});
							}
						}
					}

					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						int x = cur[0];
						int y = cur[1];

						if (map[x][y] == '$')
							documents++;

						if ((map[x][y] >= 'a' && map[x][y] <= 'z') && !key.contains(map[x][y])) {
							newKey = true;
							key.add(map[x][y]);
						}

						for (int j = 0; j < 4; j++) {
							int nx = x + dx[j];
							int ny = y + dy[j];

							if (nx < 0 || ny < 0 || nx >= map.length || ny >= map[0].length || visited[nx][ny] || !pass(map[x][y], key))
								continue;

							visited[nx][ny] = true;
							queue.offer(new int[]{nx, ny});
						}
					}

					answer[i] = documents;

					if (!newKey) break;
				}
			}
		}

		private boolean pass(char ch, Set<Character> key) {
			if (ch == '*') return false;
			if (ch == '.' || ch == '$') return true;
			if (ch >= 'a' && ch <= 'z') return true;
			if (ch >= 'A' && ch <= 'Z') {
				return key.contains(Character.toLowerCase(ch));
			}

			return false;
		}

		private void input() throws IOException {
			try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
				int tc = Integer.parseInt(br.readLine()); // 테스트 케이스

				buildings = new ArrayList<>();
				keys = new ArrayList<>();
				for (int i = 0; i < tc; i++) {
					String[] arr = br.readLine().split(" ");
					int h = Integer.parseInt(arr[0]);
					int w = Integer.parseInt(arr[1]);
					char[][] building = new char[h][w];

					for (int j = 0; j < h; j++)
						building[j] = br.readLine().toCharArray();

					buildings.add(building);

					Set<Character> key = br.readLine().chars().mapToObj(c -> (char) c).collect(Collectors.toSet());
					keys.add(key);
				}
			}
		}

		private void output() throws IOException {
			try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
				String ans = IntStream.of(answer)
					.mapToObj(String::valueOf)
					.collect(Collectors.joining("\n"));

				bw.write(ans);
				bw.flush();
			}
		}
	}
}