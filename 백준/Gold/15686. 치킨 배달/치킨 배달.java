import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		InputData input = input();
		int answer = solution(input.n, input.m, input.map, input.home, input.chicken);
		output(answer);
	}

	private static int solution(int n, int m, int[][] map, List<Point> home, List<Point> chicken) {
		// 0 : 빈칸, 1 : 집, 2 : 치킨집
		boolean[] open = new boolean[chicken.size()];
		int[] answer = {Integer.MAX_VALUE};
		dfs(m, home, chicken, 0, 0, open, answer);

		return answer[0];
	}

	private static void dfs(int m, List<Point> home, List<Point> chicken, int start, int count, boolean[] open, int[] answer) {
		if (m == count) {
			int result = 0;

			for (int i = 0; i < home.size(); i++) {
				int temp = Integer.MAX_VALUE;

				for (int j = 0; j < chicken.size(); j++) {
					if (!open[j]) continue;

					int distance = Math.abs(home.get(i).x - chicken.get(j).x) + Math.abs(home.get(i).y - chicken.get(j).y);
					temp = Math.min(temp, distance);
				}
				result += temp;
			}

			answer[0] = Math.min(answer[0], result);
			return;
		}

		for (int i = start; i < chicken.size(); i++) {
			open[i] = true;
			dfs(m, home, chicken, i + 1, count + 1, open, answer);
			open[i] = false;
		}
	}

	private static InputData input() throws IOException {
		List<Point> home = new ArrayList<>();
		List<Point> chicken = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nm = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		int[][] map = new int[nm[0]][nm[0]];
		for (int i = 0; i < nm[0]; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			for (int j = 0; j < nm[0]; j++) {
				if (map[i][j] == 1) {
					home.add(new Point(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Point(i, j));
				}
			}
		}

		return new InputData(nm[0], nm[1], map, home, chicken);
	}
    
    private static void output(int answer) {
        System.out.println(answer);
    }

	private static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static class InputData{
		int n;
		int m;
		int[][] map;
		List<Point> home;
		List<Point> chicken;

		public InputData(int n, int m, int[][] map, List<Point> home, List<Point> chicken) {
			this.n = n;
			this.m = m;
			this.map = map;
			this.home = home;
			this.chicken = chicken;
		}
	}
}
