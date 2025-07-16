import java.util.*;

class Solution {
   public static int solution(int[][] points, int[][] routes) {
		/*
		 * points[i] = [r, c];
		 * n = points.length;
		 * 각 포인트는 1 ~ n까지

		 * x대 경로 = routes.length;
		 *
		 */
		int answer = 0;

		List<List<Position>> paths = new ArrayList<>(); // 로봇 이동 경로
		for (int[] route : routes) {
			List<Position> path = calculate(points, route);
			paths.add(path);
		}

		int time = 0; // 이동 시간
		for (List<Position> path : paths) {
			time = Math.max(time, path.size());
		}

		for (int i = 0; i < time; i++) {
			Map<Position, Integer> counts = new HashMap<>();

			for (List<Position> path : paths) {
				if (i >= path.size()) continue;

				Position pos = path.get(i);
				counts.put(pos, counts.getOrDefault(pos, 0) + 1);
			}

			for (int count : counts.values()) {
				if (count >= 2) answer++;
			}
		}

		return answer;
	}

	private static List<Position> calculate(int[][] points, int[] route) {
		List<Position> path = new ArrayList<>();

		// 시작점
		Position current = new Position(points[route[0] - 1][0], points[route[0] - 1][1]);
		path.add(current);

		for (int i = 1; i < route.length; i++) {
			Position target = new Position(points[route[i] - 1][0], points[route[i] - 1][1]);

			while (current.r != target.r || current.c != target.c) {
				if (current.r != target.r) {
					int newR = current.r + Integer.signum(target.r - current.r);
					current = new Position(newR, current.c);
				} else {
					int newC = current.c + Integer.signum(target.c - current.c);
					current = new Position(current.r, newC);
				}

				path.add(current);
			}
		}

		return path;
	}

	private static class Position{
		int r;
		int c;

		public Position(int r, int c) {
			this.r = r;
			this.c = c;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			Position position = (Position) obj;
			return r == position.r && c == position.c;
		}

		@Override
		public int hashCode() {
			return r * 31 + c;
		}
	}
}